package com.xpring.edu.services;

import com.xpring.edu.model.Transaction;
import com.xpring.edu.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;

    public int saveTransaction(Transaction transaction) {
        return transactionRepository.saveTransaction(transaction);
    }

    public Transaction getTransaction(int transactionID) {
        return transactionRepository.getTransaction(transactionID);
    }
}
