package com.maistruks.portfolio.controller;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CvController {

    @RequestMapping(method = RequestMethod.GET, value = "/cv")
    public ModelAndView cv(ServletRequest request) {
        String name1 = request.getParameter("name1");
        System.out.println(name1);
        ModelAndView mav = new ModelAndView("cv");
        
        return mav;
    }
}
