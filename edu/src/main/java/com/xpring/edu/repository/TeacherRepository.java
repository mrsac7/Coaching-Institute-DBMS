package com.xpring.edu.repository;

import java.util.List;

import com.xpring.edu.model.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Teacher getTeacherByUsername(String username) {
        try {
            String sql = "SELECT * FROM Teacher WHERE username = " + "'" + username + "'";
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Teacher.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void saveTeacher(Teacher teacher) {
        String sql = "INSERT INTO Teacher(username) VALUES (?)";
        jdbcTemplate.update(sql, new Object[] { teacher.getUsername() });
    }
    
    public void updateTeacher(Teacher teacher) {
        String sql = "UPDATE Teacher SET first_name = ?, middle_name = ?, last_name = ?, sex = ?, date_of_birth = ?, contact_no = ?, house_no = ?, street = ?, city = ?, pin = ?, qualification = ?, experience = ?  WHERE teacherID ="
                + teacher.getTeacherID();
        jdbcTemplate.update(sql,
                new Object[] { teacher.getFirstName(), teacher.getMiddleName(), teacher.getLastName(), teacher.getSex(),
                        teacher.getDateOfBirth(), teacher.getContactNo(), teacher.getHouseNo(), teacher.getStreet(),
                        teacher.getCity(), teacher.getPin(), teacher.getQualification(), teacher.getExperience() });
    }

    public List<Teacher> getAll() {
        String sql = "SELECT teacherid, first_name, middle_name, last_name FROM teacher";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Teacher.class));
    }
}
