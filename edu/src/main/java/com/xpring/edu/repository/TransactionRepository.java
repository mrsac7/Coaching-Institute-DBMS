package com.xpring.edu.repository;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;

import com.xpring.edu.model.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


@Repository
public class TransactionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int saveTransaction(Transaction transaction) {
        String sql = "INSERT INTO Transaction(account_no, amount, date, time, mode)  VALUES("+
        "'"+transaction.getAccountNo()+"'"+","+
        "'"+transaction.getAmount()+"'"+","+
        "'"+LocalDate.now()+"'"+","+
        "'"+LocalTime.now()+"'"+","+
        "'"+transaction.getMode()+"'"+")";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"transactionid"});
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public Transaction getTransaction(int transactionID) {
        try{
            String sql = "SELECT * FROM Transaction WHERE transactionid = "+transactionID;
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Transaction.class));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
