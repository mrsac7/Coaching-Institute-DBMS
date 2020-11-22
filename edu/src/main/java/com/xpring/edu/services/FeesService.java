package com.xpring.edu.services;

import com.xpring.edu.model.Fees;
import com.xpring.edu.repository.FeesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeesService {
    
    @Autowired
    private FeesRepository feesRepository;

    public Fees getFees(String batchID) {
        return feesRepository.getFees(batchID);
    }
}
