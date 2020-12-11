package com.xpring.edu.controller;

import java.security.Principal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.xpring.edu.model.Student;
import com.xpring.edu.model.StudentAttendance;
import com.xpring.edu.model.Teacher;
import com.xpring.edu.model.TeacherAttendance;
import com.xpring.edu.model.User;

import com.xpring.edu.util.CustomAttendance;
import com.xpring.edu.util.CustomStudent;
import com.xpring.edu.util.CustomTeacher;
import com.xpring.edu.util.DaysFromMonth;
import com.xpring.edu.services.StudentAttendanceService;
import com.xpring.edu.services.StudentService;
import com.xpring.edu.services.TeacherAttendanceService;
import com.xpring.edu.services.TeacherService;
import com.xpring.edu.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AttendaceController {

    @Autowired
    private StudentAttendanceService studentAttendanceService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherAttendanceService teacherAttendanceService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    
    @GetMapping("/student_attendance")
    public String takeAttendance(Model model) {
        return "student_attendance";
    }

    @GetMapping("/teacher_attendance")
    public String teacherAttendance(Model model) {
        return "teacher_attendance";
    }

    @PostMapping("/student_attendance")
    public String processAttendance(Model model, @RequestParam("date") String date,
            @RequestParam("batchID") String batchID) {
        List<Student> list = studentService.getAllByBatch(batchID);
        List<StudentAttendance> attendance = studentAttendanceService.getAttendance(date, batchID);
        List<CustomStudent> allStudents = new ArrayList<CustomStudent>();
        for (Student student : list) {
            int flag = 0;
            for (StudentAttendance u : attendance) {
                if (u.getStudentID() == student.getStudentID()) {
                    flag = 1;
                    break;
                }
            }
            CustomStudent s = new CustomStudent();
            s.setStudentID(student.getStudentID());
            s.setFirstName(student.getFirstName());
            s.setMiddleName(student.getMiddleName());
            s.setLastName(student.getLastName());
            s.setStatus((flag > 0 ? "Present" : "Absent"));
            allStudents.add(s);

        }
        model.addAttribute("allStudents", allStudents);
        model.addAttribute("date", date);
        model.addAttribute("batchID", batchID);
        return "student_attendance";
    }

    @PostMapping("/teacher_attendance")
    public String processTeacherAttendance(Model model, @RequestParam("date") String date) {
        List<User> list = userService.getAllTeachers();
        List<TeacherAttendance> attendance = teacherAttendanceService.getAttendance(date);
        List<CustomTeacher> allTeachers = new ArrayList<CustomTeacher>();
        for (User user : list) {
            int flag = 0;
            Teacher teacher = teacherService.getTeacherByUsername(user.getUsername());
            for (TeacherAttendance u : attendance) {
                if (u.getTeacherID() == teacher.getTeacherID()) {
                    flag = 1;
                    break;
                }
            }
            CustomTeacher s = new CustomTeacher();
            s.setTeacherID(teacher.getTeacherID());
            s.setFirstName(teacher.getFirstName());
            s.setMiddleName(teacher.getMiddleName());
            s.setLastName(teacher.getLastName());
            s.setStatus((flag > 0 ? "Present" : "Absent"));
            allTeachers.add(s);
        }
        model.addAttribute("allTeachers", allTeachers);
        model.addAttribute("date", date);
        return "teacher_attendance";
    }

    @PostMapping("/student_attendance/{studentID}")
    public String addAttendance(Model model, @RequestParam("date") String date, @RequestParam("status") String status,
            StudentAttendance attendance) {
        if (attendance.getStatus().equals("Present")) {
            studentAttendanceService.saveAttendance(attendance);
        } else if (attendance.getStatus().equals("Absent")) {
            studentAttendanceService.removeAttendance(attendance);
        }
        return processAttendance(model, date, attendance.getBatchID());
    }

    @PostMapping("/teacher_attendance/{teacherID}")
    public String addTeacherAttendance(Model model, @RequestParam("date") String date, @RequestParam("status") String status,
            TeacherAttendance attendance) {
        if (attendance.getStatus().equals("Present")) {
            teacherAttendanceService.saveAttendance(attendance);
        } else if (attendance.getStatus().equals("Absent")) {
            teacherAttendanceService.removeAttendance(attendance);
        }
        return processTeacherAttendance(model, date);
    }

    @GetMapping("/attendance")
    public String attendance(Model model, User user, Principal principal) {
        model.addAttribute("user", user);
        return "redirect:/attendance/" + principal.getName();
    }

    @GetMapping("/attendance/{username}")
    public String attendance(Model model) {
        return "attendance";
    }

    @PostMapping("/attendance/{username}")
    public String processAttendance(@PathVariable String username, @RequestParam("date") String date, Model model) {

        User user = userService.getUser(username);
        if (user == null) {
            return "redirect:/home";
        } else if (user.getRole().equals("ROLE_STUDENT")) {
            Student student = studentService.getStudentByUsername(username);
            List<StudentAttendance> attendance = studentAttendanceService.getStudentAttendance(student.getStudentID(),
                    date);
            List<CustomAttendance> attendanceList = new ArrayList<CustomAttendance>();
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            DaysFromMonth dfm = new DaysFromMonth();
            int days = dfm.getDays(date);
            int month = Integer.parseInt(date.substring(date.length() - 2));
            int year = Integer.parseInt(date.substring(0, 4));
            if (year == calendar.get(Calendar.YEAR) && month == calendar.get(Calendar.MONTH) + 1) {
                days = calendar.get(Calendar.DAY_OF_MONTH);
            }

            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 1; i <= days; i++) {
                String d = date + "-" + (i < 10 ? "0" : "") + i;
                int flag = 0;

                for (StudentAttendance u : attendance) {
                    if (ft.format(u.getDate()).equals(d)) {
                        flag = 1;
                        break;
                    }
                }
                CustomAttendance a = new CustomAttendance();
                a.setDate(ft.parse(d, new ParsePosition(0)));
                a.setStatus((flag > 0 ? "Present" : "Absent"));
                attendanceList.add(a);
            }
            model.addAttribute("attendanceList", attendanceList);

        } else if (user.getRole().equals("ROLE_TEACHER")) {
            Teacher teacher = teacherService.getTeacherByUsername(username);
            List<TeacherAttendance> attendance = teacherAttendanceService.getTeacherAttendance(teacher.getTeacherID(),
                    date);
            List<CustomAttendance> attendanceList = new ArrayList<CustomAttendance>();
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            DaysFromMonth dfm = new DaysFromMonth();
            int days = dfm.getDays(date);
            int month = Integer.parseInt(date.substring(date.length() - 2));
            int year = Integer.parseInt(date.substring(0, 4));
            if (year == calendar.get(Calendar.YEAR) && month == calendar.get(Calendar.MONTH) + 1) {
                days = calendar.get(Calendar.DAY_OF_MONTH);
            }

            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 1; i <= days; i++) {
                String d = date + "-" + (i < 10 ? "0" : "") + i;
                int flag = 0;

                for (TeacherAttendance u : attendance) {
                    if (ft.format(u.getDate()).equals(d)) {
                        flag = 1;
                        break;
                    }
                }
                CustomAttendance a = new CustomAttendance();
                a.setDate(ft.parse(d, new ParsePosition(0)));
                a.setStatus((flag > 0 ? "Present" : "Absent"));
                attendanceList.add(a);
            }
            model.addAttribute("attendanceList", attendanceList);
        }
        model.addAttribute("date", date);
        return "attendance";
    }
}
