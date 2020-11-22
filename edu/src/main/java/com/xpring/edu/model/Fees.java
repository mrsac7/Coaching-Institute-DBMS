package com.xpring.edu.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fees")
public class Fees {
    
    @Id
    private String batchID;
    private int amount;

    public String getBatchID() {
        return batchID;
    }

    public void setBatchID(String batchID) {
        this.batchID = batchID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public Fees() {

    }

    public Fees(String batchID, int amount) {
        this.batchID = batchID;
        this.amount = amount;
    }

    
}
