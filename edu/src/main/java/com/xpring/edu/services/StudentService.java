package com.xpring.edu.services;

import java.util.List;

import com.xpring.edu.model.Student;
import com.xpring.edu.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GuardianService guardianService;

    public Student getStudentByUsername(String username) {
        return studentRepository.getStudentByUsername(username);
    }

    public Student getStudentByID(int studentID) {
        return studentRepository.getStudentByID(studentID);
    }

    public void createStudentAndGuardianByUsername(String username) {
        Student student = new Student();
        student.setUsername(username);
        studentRepository.saveStudent(student);
        student = getStudentByUsername(username);
        guardianService.createGuardianByStudentID(student.getStudentID());
    }

    public void updateStudent(Student student) {
        studentRepository.updateStudent(student);
    }

    public List<Student> getAllByBatch(String batchID) {
        return studentRepository.getAllByBatch(batchID);
    }
}
