package com.xpring.edu.controller;

import java.security.Principal;

import java.util.List;

import com.xpring.edu.model.Enrollment;
import com.xpring.edu.model.Student;
import com.xpring.edu.model.Transaction;
import com.xpring.edu.model.User;
import com.xpring.edu.services.EnrollmentService;
import com.xpring.edu.services.FeesService;
import com.xpring.edu.services.StudentService;
import com.xpring.edu.services.TransactionService;
import com.xpring.edu.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EnrollmentController {
    
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private FeesService feesService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @GetMapping("/view_enrollment")
    public String view(Model model) {
        List<Enrollment> list = enrollmentService.getAll();
        model.addAttribute("list", list);
        return "view_enrollment";
    }

    @GetMapping("/transaction/{transactionID}")
    public String getTransaction(Model model, @PathVariable int transactionID) {
        Transaction transaction = transactionService.getTransaction(transactionID);
        model.addAttribute("transaction", transaction);
        return "view_transaction";
    }

    @GetMapping("/enrollment")
    public String enrollment(Model model) {
        return "enrollment";
    }

    @PostMapping("/enrollment")
    public String process(Model model, @RequestParam("studentID") int studentID, @RequestParam("batchID") String batchID) {
        Student student = studentService.getStudentByID(studentID);
        String name = student.getName();
        Enrollment enrollment = new Enrollment();
        enrollment.setBatchID(batchID);
        enrollment.setStudentID(studentID);
        return newEnrollment(model, name, enrollment);
    }

    @GetMapping("/enrollment/{username}")
    public String studentEnrollment(Model model, @PathVariable String username) {
        User user = userService.getUser(username);
        if (user == null) {
            return "redirect:/home";
        } else if (user.getRole().equals("ROLE_STUDENT")) {
            Student student = studentService.getStudentByUsername(username);
            model.addAttribute("student", student);
        }
        return "enrollment";
    }
    
    @PostMapping("/enrollment/new")
    public String newEnrollment(Model model, @RequestParam("name") String name, Enrollment enrollment) {
        model.addAttribute("name", name);
        model.addAttribute("enrollment", enrollment);
        model.addAttribute("fees", feesService.getFees(enrollment.getBatchID()));
        return "process_enrollment";
    }

    @PostMapping("/enrollment/process")
    public String processEnrollment(Model model, Enrollment enrollment, Transaction transaction, Principal principal) {
        int transactionID = transactionService.saveTransaction(transaction);
        enrollment.setTransactionID(transactionID);
        enrollmentService.saveEnrollment(enrollment);
        model.addAttribute("message", "Enrollment Successfull!");
        String username = principal.getName();
        User user = userService.getUser(username);
        if (user.getRole().equals("ROLE_STUDENT")){
            return studentEnrollment(model, user.getUsername());
        }
        else {
            return enrollment(model);
        }
    }
}
