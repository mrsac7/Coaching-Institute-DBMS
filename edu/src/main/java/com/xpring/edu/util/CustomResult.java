package com.xpring.edu.util;

public class CustomResult {

    private int testID;
    private String testName;
    private int studentID;
    private String firstName;
    private String middleName;
    private String lastName;
    private int marks;
    private Double percent;
    private int rank;
    private String courseID;
    private int totalMarks;

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public CustomResult() {
        
    }

    public CustomResult(int testID, String testName, int studentID, String firstName, String middleName,
            String lastName, int marks, Double percent, int rank, String courseID, int totalMarks) {
        this.testID = testID;
        this.testName = testName;
        this.studentID = studentID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.marks = marks;
        this.percent = percent;
        this.rank = rank;
        this.courseID = courseID;
        this.totalMarks = totalMarks;
    }
 
    
}