package com.ownproject.Sorganizer;

/*CREATE TABLE IF NOT EXISTS REPREQUEST (
        noteid INT PRIMARY KEY auto_increment,
        created_at TIMESTAMP,
        clientname VARCHAR(20),
        licenceplate VARCHAR(20),
        vinnumber VARCHAR(20),
        defectdescription VARCHAR (1000),
        userid INT,
        foreign key (userid) references USERS(userid)
        );*/

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="reprequest")
public class RepRequest {
    @Id
    @GeneratedValue
    private Integer repReqId;
    @CreationTimestamp
    private Timestamp timeStamp;
    private String clientName;
    private String defectDescription;
    private String licencePlate;
    private String vinNumber;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    private Boolean isPartsOrdered;
    private Boolean isScheduled;
    private Boolean isFinished;

    public RepRequest() {
    }


    public RepRequest(Integer repReqId, String clientName, String defectDescription, String licencePlate, String vinNumber, User user, Boolean isPartsOrdered, Boolean isScheduled, Boolean isFinished) {
        this.repReqId = repReqId;
        this.clientName = clientName;
        this.defectDescription = defectDescription;
        this.licencePlate = licencePlate;
        this.vinNumber = vinNumber;
        this.user = user;
        this.isPartsOrdered = isPartsOrdered;
        this.isScheduled = isScheduled;
        this.isFinished = isFinished;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public Integer getRepReqId() {
        return repReqId;
    }

    public void setRepReqId(Integer repReqId) {
        this.repReqId = repReqId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDefectDescription() {
        return defectDescription;
    }

    public void setDefectDescription(String defectDescription) {
        this.defectDescription = defectDescription;
    }

    public User getUserId() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public Boolean getPartsOrdered() {
        return isPartsOrdered;
    }

    public void setPartsOrdered(Boolean partsOrdered) {
        isPartsOrdered = partsOrdered;
    }

    public Boolean getScheduled() {
        return isScheduled;
    }

    public void setScheduled(Boolean scheduled) {
        isScheduled = scheduled;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
