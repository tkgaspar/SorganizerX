package com.ownproject.Sorganizer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.HOURS;


@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private RepRequestRepository repRequestRepository;
    @Autowired
    private MechanicRepository mechanicRepository;

    @Autowired
    private RepReqService repReqService;

    private int scheduleCounter;


    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getAllSchedules() {
        return this.scheduleRepository.findAll();
    }

    public Schedule getScheduleById(Integer scheduleId) {
        return this.scheduleRepository.findById(scheduleId).get();
    }

    public Schedule getScheduleByRepReqId(Integer repReqId) {
        return this.scheduleRepository.findByRepreqId(repReqId);
    }

    public List<Schedule> getSchedulesByDate(LocalDate reparationDate) {
        return this.scheduleRepository.findByDate(/*java.sql.Date.valueOf*/reparationDate);
    }

    public List<Mechanic> allMechanics() {
        return this.mechanicRepository.findAll();
    }

    public List<LocalDate> getAllScheduledDates() {
        List<LocalDate> localDates = new ArrayList<>();
        this.scheduleRepository.getAllScheduledDates().forEach(i -> {
            localDates.add(i.toLocalDate());
        });
        return localDates.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    public Schedule addSchedule(ScheduleForm scheduleForm) {
        Schedule schedule = new Schedule();
        schedule.setMechanic(mechanicRepository.findByMechanic(scheduleForm.getMechanic()));
        schedule.setBeginningTime(Instant.parse(scheduleForm.getBeginningTime()));
        schedule.setEndTime(schedule.getBeginningTime().plus(scheduleForm.getDuration(), HOURS));
        schedule.setDuration(scheduleForm.getDuration());
        schedule.setRepreqId(scheduleForm.getRepReqId());
        RepRequest repRequest = this.repReqService.findById(scheduleForm.getRepReqId());
        repRequest.setScheduled(true);
        this.repReqService.save(repRequest);
        return this.scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Schedule schedule) {
        this.scheduleRepository.delete(schedule);
    }

    public void updateSchedule(ScheduleForm sch) {
        Schedule schedule = new Schedule();
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(sch.getScheduleId());
        if (optionalSchedule.isPresent()) {
            BeanUtils.copyProperties(sch, schedule);

        }
        this.scheduleRepository.save(schedule);
    }

    public Instant convertToInstant(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd'T'HH:mm");
        Instant localDate = Instant.parse(date);
        return localDate;
    }

    public Map<LocalDate, TableFormData> allSchedules() {
        Map allSchedules = new TreeMap<LocalDate, TableFormData>();
        getAllScheduledDates().forEach(i -> {
            allSchedules.put(i, tableData(getSchedulesByDate(i)));
        });
        return allSchedules;
    }

    public TableFormData tableData(List<Schedule> schedulesOfDate) {
        TableFormData tableFormData = new TableFormData();
        Map<String, List<TableCellData>> timeTable = new TreeMap<>();
        ZonedDateTime zdt= schedulesOfDate.get(0).getBeginningTime().atZone(ZoneId.systemDefault());
        tableFormData.setDateOfTable(zdt.toLocalDate());
        for (Schedule schedule : schedulesOfDate) {
            allMechanics().forEach(i -> {
                List<Schedule> mechByDate = new ArrayList<>();
                mechByDate.addAll(scheduleRepository.findAllByMechanicAndDate(i.getMechId(), tableFormData.getDateOfTable()));
                if (mechByDate.isEmpty()) {
                    List<TableCellData> emptyList = new ArrayList<TableCellData>(new ArrayList<TableCellData>(Arrays.asList(new TableCellData[18]))); //List created only to be passed to Thymeleaf, defines tablecells classname as not scheduled
                    Collections.fill(emptyList, new TableCellData(false, "", null));
                    timeTable.put(i.getMechanicName(), emptyList);
                } else {
                    timeTable.put(i.getMechanicName(), cellsOfLine1(mechByDate));
                }
            });
        }
        tableFormData.setTimeTable(timeTable);
        return tableFormData;
    }


    public List<TableCellData> cellsOfLine1(List<Schedule> mechByDate) {
        List<TableCellData> cellsOfLine1 = new ArrayList<TableCellData>(Arrays.asList(new TableCellData[18]));
        Collections.fill(cellsOfLine1, new TableCellData(false, "", null));
        String[] wH = {"08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30"};
        List<String> workingHours = Arrays.asList(wH.clone());
        scheduleCounter = 0;
        mechByDate.forEach(i -> {
            for (int j = 0; j < workingHours.size(); j++) {
                if (workingHours.get(j).equals(i.getBeginningTime().toString().substring(11, 16))) {
                    cellsOfLine1.set(j, new TableCellData(true, repRequestRepository.findById(i.getRepreqId()).get().getLicencePlate() + " // " + repRequestRepository.findById(i.getRepreqId()).get().getDefectDescription(), i.getDuration() * 2));
                    scheduleCounter++;
                }
            }
        });
        List<Integer> indexesToBeRemoved = new ArrayList<>();
        if (scheduleCounter > 0) {
            for (int i = 0; i < cellsOfLine1.size(); i++) {
                if (cellsOfLine1.get(i).isScheduled()) {
                    for (int j = 1; j < cellsOfLine1.get(i).getColspan(); j++) {
                        indexesToBeRemoved.add(i + j);
                    }
                }
            }
            indexesToBeRemoved.forEach(x -> cellsOfLine1.set(x, null));
            cellsOfLine1.removeAll(Collections.singletonList(null));
        }
        return cellsOfLine1;
    }
}
