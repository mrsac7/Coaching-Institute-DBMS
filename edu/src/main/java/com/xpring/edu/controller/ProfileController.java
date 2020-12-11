package com.xpring.edu.controller;

import java.security.Principal;

import com.xpring.edu.model.Guardian;
import com.xpring.edu.model.Student;
import com.xpring.edu.model.Teacher;
import com.xpring.edu.model.User;
import com.xpring.edu.services.GuardianService;
import com.xpring.edu.services.StudentService;
import com.xpring.edu.services.TeacherService;
import com.xpring.edu.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    
    @Autowired
    private GuardianService guardianService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(Model model, User user, Principal principal) {
        model.addAttribute("user", user);
        return "redirect:/profile/" + principal.getName();
    }

    @GetMapping("/profile/{username}")
    public String profile(@PathVariable String username, Model model) {
        User user = userService.getUser(username);
        model.addAttribute("userX", user);
        if (user == null) {
            return "redirect:/home";
        } else if (user.getRole().equals("ROLE_STUDENT")) {
            Student student = studentService.getStudentByUsername(username);
            Guardian guardian = guardianService.getGuardian(student.getStudentID());
            model.addAttribute("student", student);
            model.addAttribute("guardian", guardian);
        } else if (user.getRole().equals("ROLE_TEACHER")) {
            Teacher teacher = teacherService.getTeacherByUsername(username);
            model.addAttribute("userX", user);
            model.addAttribute("teacher", teacher);
        }
        return "profile";
    }

    @GetMapping("/profile/{username}/edit")
    public String editProfile(@PathVariable String username, Model model) {
        User user = userService.getUser(username);
        model.addAttribute("userX", user);
        
        if (user.getRole().equals("ROLE_STUDENT")) {
            Student student = studentService.getStudentByUsername(username);
            Guardian guardian = guardianService.getGuardian(student.getStudentID());
            model.addAttribute("student", student);
            model.addAttribute("guardian", guardian);
        } else if (user.getRole().equals("ROLE_TEACHER")) {
            Teacher teacher = teacherService.getTeacherByUsername(username);
            model.addAttribute("teacher", teacher);
        }
        return "edit_profile";
    }

    @PostMapping("/profile/{username}/update_student")
    public String updateStudentProfile(@PathVariable String username, Student student, Guardian guardian, Model model) {
        studentService.updateStudent(student);
        guardianService.updateGuardian(guardian);
        return "redirect:/profile/" + username;
    }

    @PostMapping("/profile/{username}/update_teacher")
    public String updateTeacherProfile(@PathVariable String username, Teacher teacher, Model model) {
        teacherService.updateTeacher(teacher);
        return "redirect:/profile/" + username;
    }
}
