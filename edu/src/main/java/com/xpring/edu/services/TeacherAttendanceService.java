package com.xpring.edu.services;

import java.util.List;

import com.xpring.edu.model.TeacherAttendance;

import com.xpring.edu.repository.TeacherAttendanceRepository;
import com.xpring.edu.util.DaysFromMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherAttendanceService {
    
    @Autowired
    private TeacherAttendanceRepository teacherAttendanceRepository;

    public List<TeacherAttendance> getTeacherAttendance(int teacherID, String date) {
        DaysFromMonth daysFromMonth = new DaysFromMonth();
        int days = daysFromMonth.getDays(date);
        String startDate = date + "-1";
        String endDate = date + "-" + days;
        return teacherAttendanceRepository.getTeacherAttendance(teacherID, startDate, endDate);
    }
}
