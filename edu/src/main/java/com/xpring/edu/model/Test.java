package com.xpring.edu.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="test")
public class Test {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testID;
    private String courseID;
    private String testName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time startTime;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Time endTime;
    private int marks;

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Test() {
        
    }

    public Test(int testID, String courseID, String testName, Date date, Time startTime, Time endTime, int marks) {
        this.testID = testID;
        this.courseID = courseID;
        this.testName = testName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.marks = marks;
    }

    
}
