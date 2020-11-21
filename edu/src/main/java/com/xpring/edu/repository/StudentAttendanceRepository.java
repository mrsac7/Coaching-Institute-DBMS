package com.xpring.edu.repository;

import java.util.List;

import com.xpring.edu.model.StudentAttendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentAttendanceRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<StudentAttendance> getAttendance(String date, String batchID) {
        String sql = "SELECT * FROM student_attendance WHERE status = 'present' AND date ="+"'"+date+"'"+" AND batchID = "+"'"+batchID+"'";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(StudentAttendance.class));
    }

    public void saveAttendance(StudentAttendance attendance) {
        try {
            String sql = "INSERT INTO student_attendance(studentid, batchid, date, status) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, new Object[] {attendance.getStudentID(), attendance.getBatchID(), attendance.getDate(), attendance.getStatus()});
        }
        
        catch (Exception e) {

        }
    }

    public void removeAttendance(StudentAttendance attendance) {
        String sql = "DELETE FROM student_attendance WHERE studentID = ? AND date = ? AND batchID = ?";
        jdbcTemplate.update(sql, new Object[] { attendance.getStudentID(), attendance.getDate(), attendance.getBatchID()});
    }

    public List<StudentAttendance> getStudentAttendance(int studentID, String startDate, String endDate) {
        String sql = "SELECT * FROM student_attendance WHERE studentid = "+studentID+" AND date >= "+"'"+startDate+"'"+" AND date <= "+"'"+endDate+"'";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(StudentAttendance.class));
    }
}
