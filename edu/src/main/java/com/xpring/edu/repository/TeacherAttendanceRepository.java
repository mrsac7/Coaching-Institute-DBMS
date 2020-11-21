package com.xpring.edu.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.xpring.edu.model.TeacherAttendance;

@Repository
public class TeacherAttendanceRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TeacherAttendance> getTeacherAttendance(int teacherID, String startDate, String endDate) {
        String sql = "SELECT * FROM teacher_attendance WHERE teacherid = " + teacherID + " AND date >= " + "'"
                + startDate + "'" + " AND date <= " + "'" + endDate + "'";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(TeacherAttendance.class));
    }
}
