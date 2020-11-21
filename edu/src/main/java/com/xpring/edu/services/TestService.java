package com.xpring.edu.services;

import com.xpring.edu.repository.TestRepository;

import java.util.List;

import com.xpring.edu.model.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public List<Test> getAll() {
        return testRepository.getAll();
    }

    public void saveTest(Test test) {
        testRepository.saveTest(test);
    }

    public Test getTest(int testID) {
        return testRepository.getTest(testID);
    }
    
    public void updateTest(Test test) {
        testRepository.updateTest(test);
    }

    public void deleteTest(int testID) {
        testRepository.deleteTest(testID);
    }
}