package com.xpring.edu.services;

import com.xpring.edu.repository.PayrollRepository;

import java.util.List;

import com.xpring.edu.model.Payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    public List<Payroll> getAll() {
        return payrollRepository.getAll();
    }

    public void savePayroll(Payroll payroll) {
        payrollRepository.savePayroll(payroll);
    }

    public Payroll getPayroll(String refNo) {
        return payrollRepository.getPayroll(refNo);
    }

    public void updatePayroll(Payroll payroll) {
        payrollRepository.updatePayroll(payroll);
    }

    public void deletePayroll(String refNo) {
        payrollRepository.deletePayroll(refNo);
    }

    public List<Payroll> getAllPayroll(int teacherID) {
        return payrollRepository.getAllPayroll(teacherID);
    }
}