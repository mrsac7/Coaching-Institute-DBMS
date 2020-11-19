package com.xpring.edu.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
    @Id
    private String courseID;
    private String courseName;
    private String description;
    private String standard;

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public Course() {

    }

    public Course(String courseID, String courseName, String description, String standard) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.description = description;
        this.standard = standard;
    }

    
}
