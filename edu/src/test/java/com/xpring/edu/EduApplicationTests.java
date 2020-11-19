package com.xpring.edu;

import com.xpring.edu.model.Transaction;
import com.xpring.edu.repository.TransactionRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EduApplicationTests {

	@Autowired
	private TransactionRepository transactionRepository;

	@Test
	void contextLoads() {
		Transaction transaction = new Transaction();
		transaction.setAccountNo("8999999");
		transaction.setAmount("11000");
		int id = transactionRepository.saveTransaction(transaction);
		System.out.println(id);
	}

}
