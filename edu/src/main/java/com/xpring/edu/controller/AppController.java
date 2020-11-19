package com.xpring.edu.controller;

import java.security.Principal;

import com.xpring.edu.model.Guardian;
import com.xpring.edu.model.Student;
import com.xpring.edu.model.Teacher;

// import java.security.Principal;

// import java.security.Principal;
// import java.util.List;

import com.xpring.edu.model.User;
import com.xpring.edu.services.GuardianService;
import com.xpring.edu.services.StudentService;
import com.xpring.edu.services.TeacherService;
import com.xpring.edu.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// import java.security.Principal;

// import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/tests")
    public String tests(Model model) {
        return "tests";
    }

    @GetMapping("/enrollment")
    public String enrollment(Model model) {
        return "enrollment";
    }

    @GetMapping("/attendance")
    public String attendance(Model model) {
        return "attendance";
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
        // System.out.println(user.getRole());
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
}
