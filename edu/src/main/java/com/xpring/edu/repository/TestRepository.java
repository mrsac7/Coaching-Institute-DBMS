package com.xpring.edu.repository;

import java.util.List;

import com.xpring.edu.model.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Test> getAll() {
        String sql = "SELECT * FROM test";
        try {
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Test.class));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void saveTest(Test test) {
        String sql = "INSERT INTO test(courseid, test_name, date, start_time, end_time, marks) VALUES (?, ?, ?, ?, ? ,?)";
        jdbcTemplate.update(sql, new Object[]{test.getCourseID(), test.getTestName(), test.getDate(), test.getStartTime(), test.getEndTime(), test.getMarks()});
    }

    public Test getTest(int testID) {
        String sql = "SELECT * FROM test WHERE testid = "+"'"+testID+"'";
        try {
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Test.class));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public void updateTest(Test test) {
        String sql = "UPDATE test SET test_name = ?, courseid = ?, date = ?, start_time = ?, end_time = ?, marks = ? WHERE testid = ?";
        jdbcTemplate.update(sql, new Object[] {test.getTestName(), test.getCourseID(), test.getDate(), test.getStartTime(), test.getEndTime(), test.getMarks(), test.getTestID()});
    }

    public void deleteTest(int testID) {
        String sql = "DELETE FROM test WHERE testID = "+testID;
        jdbcTemplate.update(sql);
    }
}
