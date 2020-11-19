package com.xpring.edu.model;

import java.sql.Time;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "transaction")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionID;
    private String accountNo;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;
    @DateTimeFormat(pattern="HH:mm:ss")
    private Time time;
    private String amount;
    private String mode;

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Transaction() {
        
    }

    public Transaction(int transactionID, String accountNo, Date date, Time time, String amount, String mode) {
        this.transactionID = transactionID;
        this.accountNo = accountNo;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.mode = mode;
    }

   
    
}
