package com.xpring.edu.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "batch")
public class Batch {
    @Id
    private String batchID;
    private String standard;
    private String name;
    private String roomNo;

    public String getBatchID() {
        return batchID;
    }

    public void setBatchID(String batchID) {
        this.batchID = batchID;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Batch() {
        
    }

    public Batch(String batchID, String standard, String name, String roomNo) {
        this.batchID = batchID;
        this.standard = standard;
        this.name = name;
        this.roomNo = roomNo;
    }
    
}
