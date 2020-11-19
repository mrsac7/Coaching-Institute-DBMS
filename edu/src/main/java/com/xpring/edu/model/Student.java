package com.xpring.edu.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String sex;
    private String username;
    private String contactNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @ColumnDefault("0")
    private int age;
    private String houseNo;
    private String street;
    private String city;
    private String pin;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        try {
            LocalDate now = LocalDate.now();
            LocalDate dob = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Period period = Period.between(dob, now);
            return period.getYears();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public void setAge() {
        this.age = getAge();
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Student() {
        
    }

    public Student(int studentID, String firstName, String middleName, String lastName, String sex, String username,
            String contactNo, Date dateOfBirth, int age, String houseNo, String street, String city, String pin) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.sex = sex;
        this.username = username;
        this.contactNo = contactNo;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.houseNo = houseNo;
        this.street = street;
        this.city = city;
        this.pin = pin;
    }

    
}