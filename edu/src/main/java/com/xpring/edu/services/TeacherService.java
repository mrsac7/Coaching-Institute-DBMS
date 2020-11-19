package com.xpring.edu.services;

import com.xpring.edu.model.Teacher;
import com.xpring.edu.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher getTeacherByUsername(String username) {
        return teacherRepository.getTeacherByUsername(username);
    }

    public void createTeacherByUsername(String username) {
        Teacher teacher = new Teacher();
        teacher.setUsername(username);
        teacherRepository.saveTeacher(teacher);
    }

    public void updateTeacher(Teacher teacher) {
        teacherRepository.updateTeacher(teacher);
    }
}
