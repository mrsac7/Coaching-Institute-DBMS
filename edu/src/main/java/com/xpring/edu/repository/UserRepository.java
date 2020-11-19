package com.xpring.edu.repository;


import java.util.List;

import com.xpring.edu.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveUser(User user) {
        String sql = "INSERT INTO User(username, email, password, role) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, new Object[] {user.getUsername(), user.getEmail(), user.getPassword(), user.getRole()});
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM User";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(User.class));
    }
    
    public User getUser(String username) {
        try {
            String sql = "SELECT * FROM User WHERE username = "+"'"+username+"'";
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(User.class));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }        
}
