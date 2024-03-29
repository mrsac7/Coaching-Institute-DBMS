package com.xpring.edu.services;

import java.util.List;
import com.xpring.edu.model.Enrollment;
import com.xpring.edu.repository.EnrollmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public void saveEnrollment(Enrollment enrollment) {
        enrollmentRepository.saveEnrollment(enrollment);
    }

    public List<Enrollment> getAll() {
        return enrollmentRepository.getAll();
    }
}
