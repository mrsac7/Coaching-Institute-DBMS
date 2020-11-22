package com.xpring.edu.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "payroll")
public class Payroll {
    
    @Id
    private String refNo;
    private int teacherID;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date serviceDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creditDate;
    private int amount;

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Date getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(Date creditDate) {
        this.creditDate = creditDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Payroll(){
        
    }

    public Payroll(String refNo, int teacherID, Date serviceDate, Date creditDate, int amount) {
        this.refNo = refNo;
        this.teacherID = teacherID;
        this.serviceDate = serviceDate;
        this.creditDate = creditDate;
        this.amount = amount;
    }
    
    
}
