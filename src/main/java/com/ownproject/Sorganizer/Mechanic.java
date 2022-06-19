package com.ownproject.Sorganizer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
@Entity
@Table(name = "mechanics")
public class Mechanic {
    @Id
    @GeneratedValue
    private Integer mechId;
    private String mechanic;

    public Mechanic() {
    }

    public Mechanic(Integer mechId, String mechanic) {
        this.mechId = mechId;
        this.mechanic = mechanic;
    }

    public Integer getMechId() {
        return mechId;
    }

    public void setMechId(Integer mechId) {
        this.mechId = mechId;
    }

    public String getMechanicName() {
        return mechanic;
    }

    public void setMechanicName(String mechanic) {
        this.mechanic = mechanic;
    }
}
