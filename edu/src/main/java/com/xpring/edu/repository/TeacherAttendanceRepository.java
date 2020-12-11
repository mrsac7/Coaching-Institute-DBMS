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

    public List<TeacherAttendance> getAttendance(String date) {
        String sql = "SELECT * FROM teacher_attendance WHERE status = 'present' AND date =" + "'" + date + "'";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(TeacherAttendance.class));
    }

    public void saveAttendance(TeacherAttendance attendance) {
        try {
            String sql = "INSERT INTO teacher_attendance(teacherid, date, status) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, new Object[] { attendance.getTeacherID(),
                    attendance.getDate(), attendance.getStatus() });
        }

        catch (Exception e) {

        }
    }

    public void removeAttendance(TeacherAttendance attendance) {
        String sql = "DELETE FROM teacher_attendance WHERE teacherID = ? AND date = ?";
        jdbcTemplate.update(sql,
                new Object[] { attendance.getTeacherID(), attendance.getDate()});
    }

}
