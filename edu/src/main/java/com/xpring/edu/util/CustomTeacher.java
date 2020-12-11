package com.xpring.edu.util;

public class CustomTeacher {

    private String firstName;
    private String middleName;
    private String lastName;
    private String contactNo;
    private String email;
    private String qualification;
    private String experience;
    private int teacherID;
    private String status;

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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CustomTeacher() {
    }

    public CustomTeacher(String firstName, String middleName, String lastName, String contactNo, String email,
            String qualification, String experience, int teacherID, String status) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contactNo = contactNo;
        this.email = email;
        this.qualification = qualification;
        this.experience = experience;
        this.teacherID = teacherID;
        this.status = status;
    }

    
    
    
}
