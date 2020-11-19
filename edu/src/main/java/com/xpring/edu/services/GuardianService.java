package com.xpring.edu.services;

import com.xpring.edu.model.Guardian;
import com.xpring.edu.repository.GuardianRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuardianService {
    
    @Autowired
    private GuardianRepository guardianRepository;

    public void createGuardianByStudentID(int studentID) {
        Guardian guardian = new Guardian();
        guardian.setStudentID(studentID);
        guardianRepository.saveGuardian(guardian);
    }

    public Guardian getGuardian(int studentID) {
        return guardianRepository.getGuardian(studentID);
    }

    public void updateGuardian(Guardian guardian) {
        guardianRepository.updateGuardian(guardian);
    }
}
