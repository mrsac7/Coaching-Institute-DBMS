package com.xpring.edu.controller;

import java.util.List;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import com.xpring.edu.model.Payroll;
import com.xpring.edu.services.PayrollService;
import com.xpring.edu.services.TeacherService;
import com.xpring.edu.model.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PayrollController {
    
    @Autowired
    private PayrollService payrollService;
    @Autowired
    private TeacherService teacherService;

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

    @PostMapping("/payroll/add")
    public String newPayroll(Model model, @RequestParam("sdate") String sdate, Payroll payroll) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        payroll.setServiceDate(ft.parse(sdate + "-01", new ParsePosition(0)));
        payrollService.savePayroll(payroll);
        return "redirect:/payroll";
    }

    @GetMapping("/payroll/{refNo}/edit")
    public String editPayroll(@PathVariable String refNo, Model model) {
        Payroll payroll = payrollService.getPayroll(refNo);
        model.addAttribute("payroll", payroll);
        List<Teacher> list = teacherService.getAll();
        model.addAttribute("allTeacher", list);
        return "edit_payroll";
    }

    @PostMapping("payroll/{refNo}/edit")
    public String updatePayroll(Model model, @RequestParam("sdate") String sdate, Payroll payroll) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        payroll.setServiceDate(ft.parse(sdate + "-01", new ParsePosition(0)));
        payrollService.updatePayroll(payroll);
        return "redirect:/payroll";
    }

    @PostMapping("payroll/{refNo}/delete")
    public String deletePayroll(Model model, @PathVariable String refNo) {
        payrollService.deletePayroll(refNo);
        return "redirect:/payroll";
    }

    @GetMapping("payroll/{username}")
    public String getPayroll(Model model, @PathVariable String username) {
        Teacher teacher = teacherService.getTeacherByUsername(username);
        List<Payroll> list = payrollService.getAllPayroll(teacher.getTeacherID());
        model.addAttribute("teacher", teacher);
        model.addAttribute("list", list);
        return "view_payroll";
    }
}
