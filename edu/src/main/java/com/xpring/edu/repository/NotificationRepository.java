package com.xpring.edu.repository;

import com.xpring.edu.model.Notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(Notification notification) {
        String sql = "INSERT INTO notification(emailid, header, body) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, new Object[] {notification.getEmailID(), notification.getHeader(), notification.getBody()});
    }

    public void delete(int notificationID) {
        String sql = "DELETE FROM notification WHERE notificationid = "+notificationID;
        jdbcTemplate.update(sql);
    }

    public void update(Notification notification) {
        String sql = "UPDATE notification SET header = ?, body = ? WHERE notificationid = ?";
        jdbcTemplate.update(sql, new Object[] {notification.getHeader(), notification.getBody(), notification.getNotificationID()});
    }

    public List<Notification> getAll() {
        String sql = "SELECT * FROM notification";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Notification.class));
    }

    public Notification get(int notificationID) {
        String sql = "SELECT * FROM notification WHERE notificationID = "+notificationID;
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Notification.class));
    }
}
