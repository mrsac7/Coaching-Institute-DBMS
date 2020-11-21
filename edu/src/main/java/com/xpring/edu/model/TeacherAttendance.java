package com.xpring.edu.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "teacher_attendance")
public class TeacherAttendance {
    
    @Id
    private int teacherID;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String status;

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
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

    public TeacherAttendance() {
        
    }
    
    public TeacherAttendance(int teacherID, Date date, String status) {
        this.teacherID = teacherID;
        this.date = date;
        this.status = status;
    }

    

}
