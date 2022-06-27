package com.ownproject.Sorganizer;

import com.ownproject.Sorganizer.ScheduleForm;
import com.ownproject.Sorganizer.RepReqService;
import com.ownproject.Sorganizer.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;


@Controller
public class ScheduleController {

    private RepReqService repReqService;
    private ScheduleService scheduleService;

    public ScheduleController(RepReqService repReqService, ScheduleService scheduleService) {
        this.repReqService = repReqService;
        this.scheduleService = scheduleService;
    }

   /* @GetMapping("/schedule")
    public String scheduleView(@ModelAttribute("scheduleForm") ScheduleForm scheduleForm, Model model) {
        model.addAttribute("UnscheduledRequests", repReqService.getUnscheduledRepReqList());
        model.addAttribute("ScheduledRepairs", scheduleService.getAllSchedules());
        model.addAttribute("AvailableMechanics", scheduleService.allMechanics());
        model.addAttribute("localDate", Instant.now());
        model.addAttribute("ScheduledHours", scheduleService.allSchedules());
        return "schedule";
    }

  @PostMapping("/schedule")
    public ModelAndView scheduleRepair(ScheduleForm scheduleForm, ModelMap attributes) {
        if (scheduleForm.getScheduleId() == null) {
            if (Instant.parse(scheduleForm.getBeginningTime()).isBefore(Instant.now())) {
                attributes.addAttribute("noteUploadErrorBool", true);
                attributes.addAttribute("noteUploadError", "You cannot schedule a repair in the past! Click ");
            } else if (!this.scheduleService.addSchedule(scheduleForm).equals(null)) {
                attributes.addAttribute("ScheduledRepairs", scheduleService.getAllSchedules());
                attributes.addAttribute("AvailableMechanics", scheduleService.allMechanics());
                attributes.addAttribute("UnscheduledRequests", repReqService.getUnscheduledRepReqList());
                attributes.addAttribute("ScheduledHours", scheduleService.allSchedules());
            }
        } else {
            this.scheduleService.updateSchedule(scheduleForm);
            attributes.addAttribute("ScheduledRepairs", scheduleService.getAllSchedules());
            attributes.addAttribute("AvailableMechanics", scheduleService.allMechanics());
            attributes.addAttribute("UnscheduledRequests", repReqService.getUnscheduledRepReqList());
            attributes.addAttribute("ScheduledHours", scheduleService.allSchedules());
        }
        return new ModelAndView("forward:/result", attributes);
    }*/


}
