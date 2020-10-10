package com.maistruk.springapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.maistruk.springapp.exception.StudentException;
import com.maistruk.springapp.model.Student;
import com.maistruk.springapp.service.StudentService;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
    
    @Autowired
    private StudentService service;
    
    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("student/create");
        return mav;
    }
    
    @PostMapping("/create")
    public ModelAndView create(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        try {
            service.create(student);
            ModelAndView modelAndView = new ModelAndView("student/info");
            return modelAndView.addObject("info", "Student created");
        } catch (StudentException exception) {
            ModelAndView modelAndView = new ModelAndView("student/create");
            return modelAndView.addObject("info", exception.getMessage());
        }
    }
    
    @GetMapping("/update")
    public ModelAndView update() {
        ModelAndView mav = new ModelAndView("student/update");
        return mav;
    }
    
    @PostMapping("/update")
    public ModelAndView update(@RequestParam("id") Integer id, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        try {
            service.update(student);
            ModelAndView modelAndView = new ModelAndView("student/info");
            return modelAndView.addObject("info", "Student updated");
        } catch (StudentException exception) {
            ModelAndView modelAndView = new ModelAndView("student/update");
            return modelAndView.addObject("info", exception.getMessage());
        }
    }
    
    
    
    @GetMapping("/showAll")
    public ModelAndView showAll() {
        ModelAndView modelAndView = new ModelAndView("student/showAll");
        List<Student> students = service.getAll();
        modelAndView.addObject("students", students);
      //  modelAndView.addObject("students", Collections.emptyList());
        return modelAndView;
    }
    
    
    
    @GetMapping("/getById")
    public ModelAndView getById() {
        ModelAndView mav = new ModelAndView("student/getById");
        return mav;
    }
    
    @PostMapping("/getById")
    public ModelAndView getById(@RequestParam("id") Integer id) {
        List<Student> students = new ArrayList<>();
        Student student;
        try {
            student = service.getById(id);
            students.add(student);
            ModelAndView modelAndView = new ModelAndView("student/showAll");
            return modelAndView.addObject("students", students);
        } catch (StudentException exception) {
            ModelAndView modelAndView = new ModelAndView("student/getById");
            return modelAndView.addObject("info", exception.getMessage());
        }
    }
    
    
    @GetMapping("/delete")
    public ModelAndView delete() {
        ModelAndView mav = new ModelAndView("student/delete");
        return mav;
    }
    
    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam("id") Integer id) {
        try {
            service.delete(id);
            ModelAndView modelAndView = new ModelAndView("student/info");
            return modelAndView.addObject("info", "Student deleted");
        } catch (StudentException e) {
            ModelAndView modelAndView = new ModelAndView("student/delete");
            return modelAndView.addObject("info", "Student not found");
        } 
    }

}
