package com.xpring.edu.controller;

import java.util.ArrayList;
import java.util.List;

import java.security.Principal;

import com.xpring.edu.model.Notification;
import com.xpring.edu.model.Teacher;
import com.xpring.edu.model.User;
import com.xpring.edu.services.NotificationService;
import com.xpring.edu.services.StudentService;
import com.xpring.edu.services.TeacherService;
import com.xpring.edu.services.UserService;
import com.xpring.edu.util.CustomTeacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/")
    public String main(Model model){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, User user){
        model.addAttribute("user", user);
        List<Notification> list = notificationService.getAll();
        model.addAttribute("list", list);
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model, Principal principal) {
        if (principal != null) {
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/register")
    public void processRegister(User user, Model model) {
        userService.saveUser(user);
        if (user.getRole().equals("ROLE_STUDENT")) {
            System.out.println(user.getRole());

            studentService.createStudentAndGuardianByUsername(user.getUsername());
        } else if (user.getRole().equals("ROLE_TEACHER")) {
            teacherService.createTeacherByUsername(user.getUsername());
        }
        model.addAttribute("message", "User registered successfully");
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

    
    @GetMapping("/teacher")
    public String teacher(Model model) {
        List<User> list = userService.getAllTeachers();
        List<CustomTeacher> allTeachers = new ArrayList<CustomTeacher>();
        for (User u: list) {
            Teacher t = teacherService.getTeacherByUsername(u.getUsername());
            CustomTeacher ct = new CustomTeacher();
            ct.setFirstName(t.getFirstName());
            ct.setContactNo(t.getContactNo());
            ct.setMiddleName(t.getMiddleName());
            ct.setLastName(t.getLastName());
            ct.setEmail(u.getEmail());
            ct.setQualification(t.getQualification());
            ct.setExperience(t.getExperience());
            allTeachers.add(ct);
        }
        model.addAttribute("allTeachers", allTeachers);
        return "teacher";
    }

    @GetMapping("/change_password")
    public String change(Model model) {
        return "change_password";
    }

    @PostMapping("/change_password")
    public String changePassword(Model model, @RequestParam("oldpassword") String oldpassword, @RequestParam("username") String username, @RequestParam("newpassword") String newpassword, @RequestParam("newpassword2") String newpassword2) {
        if (!newpassword.equals(newpassword2)) {
            model.addAttribute("message", "Unsuccessful! Both Passwords do not match.");
            return change(model);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userService.getUser(username);
        if (encoder.matches(oldpassword, user.getPassword())) {
            user.setPassword(encoder.encode(newpassword));
            userService.updatePassword(user);
            model.addAttribute("message", "Successful!");
        }
        else {
            model.addAttribute("message", "Unsuccessful! Old Password does not match.");
        }
        return change(model);
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

    @GetMapping("/course/ix")
    public String course_ix() {
        return "course_ix";
    }

    @GetMapping("/course/x")
    public String course_x() {
        return "course_x";
    }

    @GetMapping("/course/xi")
    public String course_xi() {
        return "course_xi";
    }

    @GetMapping("/course/xii")
    public String course_xii() {
        return "course_xii";
    }
}
