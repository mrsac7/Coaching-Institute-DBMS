package com.xpring.edu.controller;

import java.sql.Time;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.xpring.edu.model.Course;
import com.xpring.edu.model.Result;
import com.xpring.edu.model.Student;
import com.xpring.edu.model.Test;
import com.xpring.edu.services.CourseService;
import com.xpring.edu.services.ResultService;
import com.xpring.edu.services.StudentService;
import com.xpring.edu.services.TestService;
import com.xpring.edu.util.CustomResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
    
    @Autowired
    private CourseService courseService;
    @Autowired
    private ResultService resultService;
    @Autowired
    private StudentService studentService;  
    @Autowired
    private TestService testService;

    @GetMapping("/test") public String test(Model model) {
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
    public String newTest(Model model, @RequestParam("stime") String stime, @RequestParam("etime") String etime,
            Test test) {
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
        test.setStartTime(new Time(ft.parse(stime, new ParsePosition(0)).getTime()));
        test.setEndTime(new Time(ft.parse(etime, new ParsePosition(0)).getTime()));
        testService.saveTest(test);
        return "redirect:/test";
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
    public String updateTest(Model model, @RequestParam("stime") String stime, @RequestParam("etime") String etime,
            Test test) {
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

    @GetMapping("test/{testID}/result")
    public String result(Model model, @PathVariable int testID) {
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
        return "redirect:/test/" + testID + "/result";
    }

    @GetMapping("result/{username}")
    public String studentResult(Model model, @PathVariable String username) {
        Student student = studentService.getStudentByUsername(username);
        List<Test> list = testService.getTestOfStudent(student.getStudentID());
        List<CustomResult> resultList = new ArrayList<CustomResult>();
        for (Test test : list) {
            Result result = resultService.getResultOfStudent(test.getTestID(), student.getStudentID());
            CustomResult cr = new CustomResult();
            cr.setTestID(test.getTestID());
            cr.setCourseID(test.getCourseID());
            cr.setTotalMarks(test.getMarks());
            cr.setTestName(test.getTestName());
            if (result == null) {
                cr.setMarks(-1);
            } else {
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
