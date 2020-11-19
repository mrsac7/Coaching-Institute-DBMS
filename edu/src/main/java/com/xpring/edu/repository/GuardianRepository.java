package com.xpring.edu.repository;

import com.xpring.edu.model.Guardian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GuardianRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveGuardian(Guardian guardian) {
        String sql = "INSERT INTO Guardian(studentID) VALUES (?)";
        jdbcTemplate.update(sql, new Object[] {guardian.getStudentID() });
    }

    public Guardian getGuardian(int studentID) {
        try {
            String sql = "SELECT * FROM Guardian WHERE studentID = " + studentID;
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Guardian.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void updateGuardian(Guardian guardian) {
        String sql = "UPDATE Guardian SET gfirst_name = ?, gmiddle_name = ?, glast_name = ?, gcontact_no = ? WHERE studentID = "+guardian.getStudentID();
        jdbcTemplate.update(sql, new Object[] {guardian.getGfirstName(), guardian.getGmiddleName(), guardian.getGlastName(), guardian.getGcontactNo()});
    }
}
