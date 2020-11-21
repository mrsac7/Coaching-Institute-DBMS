package com.xpring.edu.services;

import java.util.List;

import com.xpring.edu.repository.StudentAttendanceRepository;
import com.xpring.edu.util.DaysFromMonth;
import com.xpring.edu.model.StudentAttendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentAttendanceService {
    
    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;

    public List<StudentAttendance> getAttendance(String date, String batchID) {
        return studentAttendanceRepository.getAttendance(date, batchID);
    }

    public void saveAttendance(StudentAttendance attendance) {
        studentAttendanceRepository.saveAttendance(attendance);
    }

    public void removeAttendance(StudentAttendance attendance) {
        studentAttendanceRepository.removeAttendance(attendance);
    }

    public List<StudentAttendance> getStudentAttendance(int studentID, String date) {
        DaysFromMonth daysFromMonth = new DaysFromMonth();
        int days = daysFromMonth.getDays(date);
        String startDate = date+"-1";
        String endDate = date+"-"+days;
        return studentAttendanceRepository.getStudentAttendance(studentID, startDate, endDate);
    }

}
