package com.ownproject.Sorganizer;

import java.sql.Time;
import java.time.LocalDate;

public class ScheduleForm {
    private Integer scheduleId;
    private String mechanic;
    private String beginningTime;
    private String endTime;
    private Integer duration;
    private Integer repReqId;

    public ScheduleForm(){};

    public String getMechanic() {
        return mechanic;
    }

    public void setMechanic(String mechanic) {
        this.mechanic = mechanic;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getBeginningTime() {
        String begTime=this.beginningTime+":00Z";
        return begTime;
    }

    public void setBeginningTime(String beginningTime) {
        this.beginningTime = beginningTime;
    }

    public String getEndTime() {
        String endingTime=this.endTime+":00Z";
        return endingTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public Integer getRepReqId() {
        return repReqId;
    }

    public void setRepReqId(Integer repReqId) {
        this.repReqId = repReqId;
    }
}
