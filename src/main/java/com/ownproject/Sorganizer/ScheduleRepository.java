package com.ownproject.Sorganizer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    void deleteById(Integer id);

    Schedule findByRepreqId(Integer repreqId);

    @Query(
            value = "SELECT * FROM serviceorganizerjpa.schedule where mechanic_mech_id=:mechId and DATE(beginning_time)=:date",
            nativeQuery = true
    )
    List<Schedule> findAllByMechanicAndDate(@Param("mechId") Integer mechId, @Param("date") LocalDate date);


    @Query(value = "Select * from serviceorganizerjpa.schedule s where DATE(s.beginning_time)=:date",
            nativeQuery = true)
    List<Schedule> findByDate(@Param("date") LocalDate date);

    @Query(value = "select distinct cast(serviceorganizerjpa.schedule.beginning_time AS DATE) from schedule;",
            nativeQuery = true)
    List<Date> getAllScheduledDates();


//    @Update("UPDATE SCHEDULE SET  mechanic=#{mechanic}, beginningtime=#{beginningTime}, duration=#{duration}, endtime=#{endTime},  repreqid=#{repReqId} WHERE scheduleid=#{scheduleId}")
    //  void updateSchedule(Integer scheduleId, String mechanic, Instant beginningTime, Instant endTime, Integer repreqId);


}
