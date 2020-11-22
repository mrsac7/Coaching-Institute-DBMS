package com.xpring.edu.repository;

import java.util.List;

import com.xpring.edu.model.Result;
import com.xpring.edu.util.CustomResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ResultRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Result> getResult(int testID) {
        String sql = "SELECT * FROM result WHERE testID = "+testID;
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Result.class));
    }

    public void updateResult(Result result) {
        try {
            String sql = "INSERT INTO result(testid, studentid, marks, test_rank) VALUES (?, ?, ?, 0)";
            jdbcTemplate.update(sql, new Object[] {result.getTestID(), result.getStudentID(), result.getMarks()});
        }
        catch (Exception e) {
            String sql = "UPDATE result SET marks = ? WHERE testid = ? AND studentid = ?";
            jdbcTemplate.update(sql, new Object[] {result.getMarks(), result.getTestID(), result.getStudentID()});
        }
    }

    public void updateRank(CustomResult cr) {
        String sql = "UPDATE result SET test_rank = ? WHERE testid = ? AND studentid = ?";
        jdbcTemplate.update(sql, new Object[] {cr.getRank(), cr.getTestID(), cr.getStudentID()});
    }

    public Result getResultOfStudent(int testID, int studentID) {
        try {
            String sql = "SELECT * FROM result WHERE testid = "+testID+" AND studentid = "+studentID;
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Result.class));
        }
        catch (Exception e) {
            return null;
        }
    }

}
