package com.xpring.edu.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.xpring.edu.model.Course;

@Repository
public class CourseRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Course> getAll() {
        String sql = "SELECT courseID FROM course";
        try {
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Course.class));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Course getCourse(String courseID) {
        String sql = "SELECT * from course WHERE courseid = "+"'"+courseID+"'";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Course.class));
    }
}
