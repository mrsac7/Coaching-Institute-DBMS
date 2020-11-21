package com.xpring.edu.services;

import com.xpring.edu.repository.CourseRepository;

import java.util.List;

import com.xpring.edu.model.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAll() {
        return courseRepository.getAll();
    }
}
