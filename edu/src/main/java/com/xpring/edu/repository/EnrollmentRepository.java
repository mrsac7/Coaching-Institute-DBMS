package com.xpring.edu.repository;

import com.xpring.edu.model.Enrollment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository 
public class EnrollmentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO Enrollment(batchid, studentid, transactionid) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, new Object[] {enrollment.getBatchID(), enrollment.getStudentID(), enrollment.getTransactionID()});
    }
    
}
