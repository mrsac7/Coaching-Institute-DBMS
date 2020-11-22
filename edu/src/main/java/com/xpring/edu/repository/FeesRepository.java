package com.xpring.edu.repository;

import com.xpring.edu.model.Fees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FeesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Fees getFees(String batchID) {
        String sql = "SELECT * FROM fees WHERE batchid = "+"'"+batchID+"'";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Fees.class));
    }
    
}