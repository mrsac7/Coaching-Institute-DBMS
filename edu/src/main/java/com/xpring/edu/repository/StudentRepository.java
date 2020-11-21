package com.xpring.edu.repository;

import java.util.List;

import com.xpring.edu.model.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveStudent(Student student) {
        String sql = "INSERT INTO Student(username) VALUES (?)";
        jdbcTemplate.update(sql,
                new Object[] { student.getUsername() });
    }
    
    public Student getStudentByUsername(String username) {
        try {
            String sql = "SELECT * FROM Student WHERE username = " + "'" + username + "'";
            return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Student.class));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public void updateStudent(Student student) {
        String sql = "UPDATE Student SET first_name = ?, middle_name = ?, last_name = ?, sex = ?, date_of_birth = ?, contact_no = ?, house_no = ?, street = ?, city = ?,pin = ? WHERE studentID ="+student.getStudentID();
        jdbcTemplate.update(sql, new Object[] {student.getFirstName(), student.getMiddleName(), student.getLastName(), student.getSex(), student.getDateOfBirth(), student.getContactNo(), student.getHouseNo(), student.getStreet(), student.getCity(), student.getPin()});
    }

    public List<Student> getAllByBatch(String batchID) {
        String sql = "SELECT DISTINCT s.studentid, s.first_name, s.middle_name, s.last_name FROM Student s, Enrollment e WHERE s.studentID = e.studentID and e.batchID = "+ "'"+ batchID +"'";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Student.class));
    }
}
