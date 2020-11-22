package com.xpring.edu.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "result") 
public class Result {
    @Id
    private int testID;
    private int studentID;
    private int marks;
    private int testRank;

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Result() {

    }

    public Result(int testID, int studentID, int marks, int testRank) {
        this.testID = testID;
        this.studentID = studentID;
        this.marks = marks;
        this.testRank = testRank;
    }

    public int getTestRank() {
        return testRank;
    }

    public void setTestRank(int testRank) {
        this.testRank = testRank;
    }

    
}
