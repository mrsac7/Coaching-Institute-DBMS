package com.xpring.edu.services;

import com.xpring.edu.repository.ResultRepository;
import com.xpring.edu.util.CustomResult;

import java.util.List;

import com.xpring.edu.model.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {
    
    @Autowired
    private ResultRepository resultRepository;

    public List<Result> getResult(int testID) {
        return resultRepository.getResult(testID);
    }

    public void updateResult(Result result) {
        resultRepository.updateResult(result);
    }

    public void updateRank(CustomResult cr) {
        resultRepository.updateRank(cr);
    }
    public Result getResultOfStudent(int testID, int studentID) {
        return resultRepository.getResultOfStudent(testID, studentID);
    }
}
