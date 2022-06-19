package com.ownproject.Sorganizer;
import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="schedule")
public class Schedule {
    @Id
    @GeneratedValue
    private Integer scheduleId;
    @OneToOne
    private Mechanic mechanic;
    private Instant beginningTime;
    private Instant endTime;
    private Integer duration;
    private Integer repreqId;


    public Schedule() {
    }

    public Schedule(Integer scheduleId, Mechanic mechanic, Instant beginningTime, Instant endTime, Integer repreqId,String scheduledHours) {
        this.scheduleId = scheduleId;
        this.mechanic = mechanic;
        this.beginningTime = beginningTime;
        this.endTime = endTime;
        this.repreqId = repreqId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Instant getBeginningTime() {
        return beginningTime;
    }

    public void setBeginningTime(Instant beginningTime) {
        this.beginningTime = beginningTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Integer getRepreqId() {
        return repreqId;
    }

    public void setRepreqId(Integer repreqId) {
        this.repreqId = repreqId;
    }

}
