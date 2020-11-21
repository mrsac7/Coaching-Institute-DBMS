package com.xpring.edu.util;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CustomAttendance {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String status;

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

    public CustomAttendance() {
        
    }

    public CustomAttendance(Date date, String status) {
        this.date = date;
        this.status = status;
    }

    
 
    
}
