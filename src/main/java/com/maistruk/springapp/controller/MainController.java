package com.maistruk.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.maistruk.springapp.service.StudentService;

@Controller
public class MainController {

    @Autowired
    private StudentService service;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ModelAndView main() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }
}