package com.xpring.edu.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import com.xpring.edu.model.Payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class PayrollRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Payroll> getAll() {
        String sql = "SELECT * FROM payroll";
        try {
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Payroll.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void savePayroll(Payroll payroll) {
        String sql = "INSERT INTO payroll(ref_no, teacherid, service_date, credit_date, amount) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, new Object[] { payroll.getRefNo(), payroll.getTeacherID(), payroll.getServiceDate(),
                payroll.getCreditDate(), payroll.getAmount() });
    }

    public Payroll getPayroll(String refNo) {
        String sql = "SELECT * FROM payroll WHERE ref_no = " + "'" + refNo + "'";
        try {
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Payroll.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void updatePayroll(Payroll payroll) {
        String sql = "UPDATE payroll SET teacherid = ?, service_date = ?, credit_date = ?, amount = ? WHERE ref_no = ?";
        jdbcTemplate.update(sql, new Object[] { payroll.getTeacherID(), payroll.getServiceDate(), payroll.getCreditDate(),
                payroll.getAmount(), payroll.getRefNo() });
    }

    public void deletePayroll(String refNo) {
        String sql = "DELETE FROM payroll WHERE ref_no = " + "'"+ refNo + "'";
        jdbcTemplate.update(sql);
    }
}