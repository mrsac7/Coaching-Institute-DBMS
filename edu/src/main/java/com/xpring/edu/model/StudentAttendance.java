package com.xpring.edu.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="student_attendance")
public class StudentAttendance {
    
    @Id
    private int studentID;    
    private String batchID;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String status;

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getBatchID() {
        return batchID;
    }

    public void setBatchID(String batchID) {
        this.batchID = batchID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StudentAttendance() {
        
    }

    public StudentAttendance(int studentID, String batchID, Date date, String status) {
        this.studentID = studentID;
        this.batchID = batchID;
        this.date = date;
        this.status = status;
    }

    

    
}


