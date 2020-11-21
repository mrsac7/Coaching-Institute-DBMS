package com.xpring.edu.controller;

import java.security.Principal;
import java.sql.Time;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.xpring.edu.util.CustomAttendance;
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

// import java.security.Principal;

// import java.security.Principal;
// import java.util.List;

import com.xpring.edu.model.User;
import com.xpring.edu.services.CourseService;
import com.xpring.edu.services.EnrollmentService;
// import com.xpring.edu.services.EnrollmentService;
import com.xpring.edu.services.GuardianService;
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
            Guardian guardian = guardianService.getGuardian(student.getStudentID());
            model.addAttribute("userX", user);
            model.addAttribute("student", student);
            model.addAttribute("guardian", guardian);
        }
        return "enrollment";
    }
    

    @GetMapping("/payroll")
    public String payroll(Model model) {
        return "payroll";
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

    @PostMapping("/enrollment")
    public void processEnrollment(Model model, Student student, Enrollment enrollment, Transaction transaction) {
        int transactionID = transactionService.saveTransaction(transaction);
        // transaction = transactionService.getTransaction(transactionID);
        enrollment.setTransactionID(transactionID);
        enrollmentService.saveEnrollment(enrollment);
        model.addAttribute("message", "Enrollment Successfull!");
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

    @PostMapping("/test/add")
    public String newTest(Model model, @RequestParam("stime") String stime, @RequestParam("etime") String etime, Test test) {
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
        test.setStartTime(new Time(ft.parse(stime, new ParsePosition(0)).getTime()));
        test.setEndTime(new Time(ft.parse(etime, new ParsePosition(0)).getTime()));
        testService.saveTest(test);
        return "redirect:/test";
    }

    @GetMapping("/edit_test")
    public String edT(Principal principal){
        return "redirect:/edit_test/"+principal.getName();
    }
    @GetMapping("/test/{testID}/edit")
    public String editTest(@PathVariable int testID, Model model) {
        Test test = testService.getTest(testID);
        model.addAttribute("test", test);
        List<Course> list = courseService.getAll();
        model.addAttribute("allCourses", list);
        return "edit_test";
    }

    @PostMapping("test/{testID}/edit")
    public String updateTest(Model model, @RequestParam("stime") String stime, @RequestParam("etime") String etime, Test test) {
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
        test.setStartTime(new Time(ft.parse(stime, new ParsePosition(0)).getTime()));
        test.setEndTime(new Time(ft.parse(etime, new ParsePosition(0)).getTime()));
        testService.updateTest(test);
        return "redirect:/test";
    }

    @PostMapping("test/{testID}/delete")
    public String deleteTest(Model model, @PathVariable int testID) {
        testService.deleteTest(testID);
        return "redirect:/test";
    }
}
