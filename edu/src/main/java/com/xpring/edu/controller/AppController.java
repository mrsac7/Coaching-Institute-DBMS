package com.xpring.edu.controller;

import java.security.Principal;
import java.sql.Time;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import com.xpring.edu.util.CustomAttendance;
import com.xpring.edu.util.CustomResult;
import com.xpring.edu.util.CustomStudent;
import com.xpring.edu.util.DaysFromMonth;
import com.xpring.edu.model.Enrollment;
import com.xpring.edu.model.Guardian;
import com.xpring.edu.model.Student;
import com.xpring.edu.model.StudentAttendance;
import com.xpring.edu.model.Teacher;
import com.xpring.edu.model.TeacherAttendance;
import com.xpring.edu.model.Test;
import com.xpring.edu.model.Transaction;
import com.xpring.edu.model.Course;
import com.xpring.edu.model.Payroll;
import com.xpring.edu.model.Result;

// import java.security.Principal;

// import java.security.Principal;
// import java.util.List;

import com.xpring.edu.model.User;
import com.xpring.edu.services.CourseService;
import com.xpring.edu.services.EnrollmentService;
import com.xpring.edu.services.FeesService;
// import com.xpring.edu.services.EnrollmentService;
import com.xpring.edu.services.GuardianService;
import com.xpring.edu.services.PayrollService;
import com.xpring.edu.services.ResultService;
import com.xpring.edu.services.StudentAttendanceService;
import com.xpring.edu.services.StudentService;
import com.xpring.edu.services.TeacherAttendanceService;
import com.xpring.edu.services.TeacherService;
import com.xpring.edu.services.TestService;
import com.xpring.edu.services.TransactionService;
import com.xpring.edu.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// import java.security.Principal;

// import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GuardianService guardianService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StudentAttendanceService studentAttendanceService;

    @Autowired
    private TeacherAttendanceService teacherAttendanceService;

    @Autowired
    private TestService testService;
    
    @Autowired
    private CourseService courseService;

    @Autowired
    private PayrollService payrollService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private FeesService feesService;

    @GetMapping("/")
    public String main(Model model){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, User user){
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model, Principal principal) {
        if (principal != null) {
            return "redirect:/home";
        }
        return "register";
    }

    @GetMapping("/login")
    public String enter(Model model, Principal principal) {
        if (principal != null) {
            return "redirect:/home";
        }
        return "login";
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        return "courses";
    }

    @GetMapping("/users")
    public String uesrs(Model model) {
        model.addAttribute("allUsers", userService.findAll());
        return "users";
    }

    @GetMapping("/enrollment")
    public String enrollment(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.getUser(username);
        if (user == null) {
            return "redirect:/home";
        }
        else if (user.getRole().equals("ROLE_STUDENT")) {
            Student student = studentService.getStudentByUsername(username);
            model.addAttribute("student", student);
        }
        return "enrollment";
    } 

    @GetMapping("/profile")
    public String profile(Model model, User user, Principal principal) {
        model.addAttribute("user", user);
        return "redirect:/profile/" + principal.getName();
    }

    @GetMapping("/profile/{username}")
    public String profile(@PathVariable String username, Model model) {
        User user = userService.getUser(username);
        if (user == null) {
            return "redirect:/home";
        }
        else if (user.getRole().equals("ROLE_STUDENT")) {
            Student student = studentService.getStudentByUsername(username);
            Guardian guardian = guardianService.getGuardian(student.getStudentID());

            model.addAttribute("userX", user);
            model.addAttribute("student", student);
            model.addAttribute("guardian", guardian);
        }
        else if (user.getRole().equals("ROLE_TEACHER")) {
            Teacher teacher = teacherService.getTeacherByUsername(username);
            model.addAttribute("userX", user);
            model.addAttribute("teacher", teacher);
        }
        return "profile";
    }

    @PostMapping("/register")
    public void processRegister(User user, Model model) {
        userService.saveUser(user);
        if (user.getRole().equals("ROLE_STUDENT")) {
            System.out.println(user.getRole());

            studentService.createStudentAndGuardianByUsername(user.getUsername());
        }
        else if (user.getRole().equals("ROLE_TEACHER")) {
            teacherService.createTeacherByUsername(user.getUsername());
        }
        model.addAttribute("message", "User registered successfully");
    }

    @PostMapping("/profile/{username}/edit")
    public String editProfile(@PathVariable String username, Model model) {
        User user = userService.getUser(username);
        if (user.getRole().equals("ROLE_STUDENT")) {
            Student student = studentService.getStudentByUsername(username);
            Guardian guardian = guardianService.getGuardian(student.getStudentID());
            model.addAttribute("student", student);
            model.addAttribute("guardian", guardian);
        }
        else if (user.getRole().equals("ROLE_TEACHER")) {
            Teacher teacher = teacherService.getTeacherByUsername(username);
            model.addAttribute("teacher", teacher);
        }
        return "edit_profile";
    }

    @PostMapping("/profile/{username}/update_student")
    public String updateStudentProfile(@PathVariable String username, Student student, Guardian guardian, Model model) {
        studentService.updateStudent(student);
        guardianService.updateGuardian(guardian);
        return "redirect:/profile/"+username;
    }
    @PostMapping("/profile/{username}/update_teacher")
    public String updateTeacherProfile(@PathVariable String username, Teacher teacher, Model model) {
        teacherService.updateTeacher(teacher);
        return "redirect:/profile/"+username;
    }

    @GetMapping("/enrollment/new")
    public String newEnrollment(Model model, @RequestParam("name") String name, Enrollment enrollment) {
        model.addAttribute("name", name);
        model.addAttribute("enrollment", enrollment);
        model.addAttribute("fees", feesService.getFees(enrollment.getBatchID()));
        return "process_enrollment";
    }

    @PostMapping("/enrollment/new")
    public String processEnrollment(Model model, Enrollment enrollment, Transaction transaction, Principal principal) {
        int transactionID = transactionService.saveTransaction(transaction);
        enrollment.setTransactionID(transactionID);
        enrollmentService.saveEnrollment(enrollment);
        model.addAttribute("message", "Enrollment Successfull!");
        // Student student = studentService.getStudentByUsername(principal.getName());
        // model.addAttribute("student", student);
        return enrollment(model, principal);
    }

    @GetMapping("/take_attendance")
    public String takeAttendance(Model model) {
        return "take_attendance";
    }

    @PostMapping("/take_attendance")
    public String processAttendance(Model model, @RequestParam("date") String date, @RequestParam("batchID") String batchID) {
        List<Student> list = studentService.getAllByBatch(batchID);
        List<StudentAttendance> attendance = studentAttendanceService.getAttendance(date, batchID);
        List<CustomStudent> allStudents = new ArrayList<CustomStudent>();
        for (Student student: list) {
            int flag = 0;
            for (StudentAttendance u: attendance) {
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
            s.setStatus((flag>0?"Present":"Absent"));
            allStudents.add(s);
            
        }
        model.addAttribute("allStudents", allStudents);
        model.addAttribute("date", date);
        model.addAttribute("batchID", batchID);
        return "take_attendance";
    }

    @PostMapping("/take_attendance/{studentID}")
    public String addAttendance(Model model, @RequestParam("date") String date, @RequestParam("status") String status, StudentAttendance attendance) {
        if (attendance.getStatus().equals("Present")) {
            studentAttendanceService.saveAttendance(attendance);
        }
        else if (attendance.getStatus().equals("Absent")) {
            studentAttendanceService.removeAttendance(attendance);
        }
        return processAttendance(model, date, attendance.getBatchID());
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
            List<StudentAttendance> attendance = studentAttendanceService.getStudentAttendance(student.getStudentID(), date);
            List<CustomAttendance> attendanceList = new ArrayList<CustomAttendance>();
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            DaysFromMonth dfm = new DaysFromMonth();
            int days = dfm.getDays(date);
            int month = Integer.parseInt(date.substring(date.length()-2));
            int year = Integer.parseInt(date.substring(0, 4));
            if (year == calendar.get(Calendar.YEAR) && month == calendar.get(Calendar.MONTH) + 1) {
                days = calendar.get(Calendar.DAY_OF_MONTH);
            }

            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            for (int i = 1; i <= days; i++) {
                String d = date+"-"+ (i<10?"0":"")+i;
                int flag = 0;

                for (StudentAttendance u: attendance) {
                    if (ft.format(u.getDate()).equals(d)) {
                        flag = 1;
                        break;
                    }
                }
                CustomAttendance a = new CustomAttendance();
                a.setDate(ft.parse(d, new ParsePosition(0)));
                a.setStatus((flag>0?"Present":"Absent"));
                attendanceList.add(a);
            }
            model.addAttribute("attendanceList", attendanceList);

        } else if (user.getRole().equals("ROLE_TEACHER")) {
            Teacher teacher = teacherService.getTeacherByUsername(username);
            List<TeacherAttendance> attendance = teacherAttendanceService.getTeacherAttendance(teacher.getTeacherID(), date);
            List<CustomAttendance> attendanceList = new ArrayList<CustomAttendance>();
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            DaysFromMonth dfm = new DaysFromMonth();
            int days = dfm.getDays(date);
            int month = Integer.parseInt(date.substring(date.length()-2));
            int year = Integer.parseInt(date.substring(0, 4));
            if (year == calendar.get(Calendar.YEAR) && month == calendar.get(Calendar.MONTH) + 1) {
                days = calendar.get(Calendar.DAY_OF_MONTH);
            }

            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            for (int i = 1; i <= days; i++) {
                String d = date+"-"+ (i<10?"0":"")+i;
                int flag = 0;

                for (TeacherAttendance u: attendance) {
                    if (ft.format(u.getDate()).equals(d)) {
                        flag = 1;
                        break;
                    }
                }
                CustomAttendance a = new CustomAttendance();
                a.setDate(ft.parse(d, new ParsePosition(0)));
                a.setStatus((flag>0?"Present":"Absent"));
                attendanceList.add(a);
            }
            model.addAttribute("attendanceList", attendanceList);
        }
        model.addAttribute("date", date);
        return "attendance";
    }

    @GetMapping("/test")
    public String test(Model model) {
        List<Test> test = testService.getAll();
        model.addAttribute("allTest", test);
        return "test";
    }

    @GetMapping("/test/add")
    public String addTest(Model model) {
        List<Course> list = courseService.getAll();
        model.addAttribute("allCourses", list);
        return "add_test";
    }

    @GetMapping("/payroll")
    public String payroll(Model model) {
        List<Payroll> payroll = payrollService.getAll();
        List<Teacher> teacher = teacherService.getAll();
        model.addAttribute("allTeacher", teacher);
        model.addAttribute("allPayroll", payroll);
        return "payroll";
    }

    @GetMapping("/payroll/add")
    public String addPayroll(Model model) {
        List<Teacher> list = teacherService.getAll();
        model.addAttribute("allTeacher", list);
        return "add_payroll";
    }
    
    @PostMapping("/test/add")
    public String newTest(Model model, @RequestParam("stime") String stime, @RequestParam("etime") String etime, Test test) {
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
        test.setStartTime(new Time(ft.parse(stime, new ParsePosition(0)).getTime()));
        test.setEndTime(new Time(ft.parse(etime, new ParsePosition(0)).getTime()));
        testService.saveTest(test);
        return "redirect:/test";
    }

    @PostMapping("/payroll/add")
    public String newPayroll(Model model, @RequestParam("sdate") String sdate, Payroll payroll) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        payroll.setServiceDate(ft.parse(sdate+"-01", new ParsePosition(0)));
        payrollService.savePayroll(payroll);
        return "redirect:/payroll";
    }

    @GetMapping("/test/{testID}/edit")
    public String editTest(@PathVariable int testID, Model model) {
        Test test = testService.getTest(testID);
        model.addAttribute("test", test);
        List<Course> list = courseService.getAll();
        model.addAttribute("allCourses", list);
        return "edit_test";
    }

    @GetMapping("/payroll/{refNo}/edit")
    public String editPayroll(@PathVariable String refNo, Model model) {
        Payroll payroll = payrollService.getPayroll(refNo);
        model.addAttribute("payroll", payroll);
        List<Teacher> list = teacherService.getAll();
        model.addAttribute("allTeacher", list);
        return "edit_payroll";
    }

    @PostMapping("test/{testID}/edit")
    public String updateTest(Model model, @RequestParam("stime") String stime, @RequestParam("etime") String etime, Test test) {
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
        test.setStartTime(new Time(ft.parse(stime, new ParsePosition(0)).getTime()));
        test.setEndTime(new Time(ft.parse(etime, new ParsePosition(0)).getTime()));
        testService.updateTest(test);
        return "redirect:/test";
    }

    @PostMapping("payroll/{refNo}/edit")
    public String updatePayroll(Model model, @RequestParam("sdate") String sdate, Payroll payroll) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        payroll.setServiceDate(ft.parse(sdate + "-01", new ParsePosition(0)));
        payrollService.updatePayroll(payroll);
        return "redirect:/payroll";
    }

    @PostMapping("test/{testID}/delete")
    public String deleteTest(Model model, @PathVariable int testID) {
        testService.deleteTest(testID);
        return "redirect:/test";
    }

    @PostMapping("payroll/{refNo}/delete")
    public String deletePayroll(Model model, @PathVariable String refNo) {
        payrollService.deletePayroll(refNo);
        return "redirect:/payroll";
    }

    @GetMapping("test/{testID}/result")
    public String result(Model model, @PathVariable int testID) {
        Test test = testService.getTest(testID);
        Course course = courseService.getCourse(test.getCourseID());
        List<Student> list = studentService.getAllByBatch(course.getBatchID());
        List<Result> result = resultService.getResult(testID);
        model.addAttribute("test", test);
        List<CustomResult> resultList = new ArrayList<CustomResult>();
        for (Student s: list) {
            int marks = -1;
            for (Result r: result) {
                if (r.getStudentID() == s.getStudentID()) {
                    marks = r.getMarks();
                    break;
                }
            }            
            CustomResult cr = new CustomResult();
            cr.setTestID(testID);
            cr.setStudentID(s.getStudentID());
            cr.setFirstName(s.getFirstName());
            cr.setMiddleName(s.getMiddleName());
            cr.setLastName(s.getLastName());
            cr.setMarks(marks);
            resultList.add(cr);
            Double percent  = 0.0;
            if (marks >= 0) {
                percent = marks*100.0/test.getMarks();
            }
            cr.setPercent(percent);
        }
        Collections.sort(resultList, (m1, m2) -> m2.getMarks() - m1.getMarks());
        int rank = 0, marks = test.getMarks()+1;
        for (CustomResult cr: resultList) {
            if (cr.getMarks() == marks) {
                cr.setRank(rank);
            }
            else {
                marks = cr.getMarks();
                rank++;
                cr.setRank(rank);
            }
            resultService.updateRank(cr);
        }
        model.addAttribute("resultList", resultList);
        return "result";
    }

    @GetMapping("test/{testID}/result/edit/{studentID}")
    public String editResult(Model model, @PathVariable int testID, @PathVariable int studentID) {
        Test test = testService.getTest(testID);
        Course course = courseService.getCourse(test.getCourseID());
        List<Student> list = studentService.getAllByBatch(course.getBatchID());
        List<Result> result = resultService.getResult(testID);
        model.addAttribute("test", test);
        List<CustomResult> resultList = new ArrayList<CustomResult>();
        for (Student s : list) {
            int marks = -1;
            for (Result r : result) {
                if (r.getStudentID() == s.getStudentID()) {
                    marks = r.getMarks();
                    break;
                }
            }
            CustomResult cr = new CustomResult();
            cr.setTestID(testID);
            cr.setStudentID(s.getStudentID());
            cr.setFirstName(s.getFirstName());
            cr.setMiddleName(s.getMiddleName());
            cr.setLastName(s.getLastName());
            cr.setMarks(marks);
            resultList.add(cr);
            Double percent = 0.0;
            if (marks >= 0) {
                percent = marks * 100.0 / test.getMarks();
            }
            cr.setPercent(percent);
        }
        Collections.sort(resultList, (m1, m2) -> m2.getMarks() - m1.getMarks());
        int rank = 0, marks = test.getMarks() + 1;
        for (CustomResult cr : resultList) {
            if (cr.getMarks() == marks) {
                cr.setRank(rank);
            } else {
                marks = cr.getMarks();
                rank++;
                cr.setRank(rank);
            }
            resultService.updateRank(cr);
        }
        model.addAttribute("resultList", resultList);
        model.addAttribute("toEdit", studentID);
        return "edit_result";
    }

    @PostMapping("test/{testID}/result/edit/{studentID}")
    public String processEditResult(Model model, @PathVariable int testID, Result result) {
        resultService.updateResult(result);
        return "redirect:/test/"+testID+"/result";
    }

    @GetMapping("result/{username}")
    public String studentResult(Model model, @PathVariable String username) {
        Student student = studentService.getStudentByUsername(username);
        List<Test> list = testService.getTestOfStudent(student.getStudentID());
        List<CustomResult> resultList = new ArrayList<CustomResult>();
        for (Test test: list) {
            Result result = resultService.getResultOfStudent(test.getTestID(), student.getStudentID());
            CustomResult cr = new CustomResult();
            cr.setTestID(test.getTestID());
            cr.setCourseID(test.getCourseID());
            cr.setTotalMarks(test.getMarks());
            cr.setTestName(test.getTestName());
            if (result == null) {
                cr.setMarks(-1);
            }
            else {
                cr.setMarks(result.getMarks());
                cr.setPercent(result.getMarks() * 100.0 / test.getMarks());
                cr.setRank(result.getTestRank());
            }
            resultList.add(cr);
        }
        model.addAttribute("resultList", resultList);
        model.addAttribute("student", student);
        return "student_result";
    }
}
