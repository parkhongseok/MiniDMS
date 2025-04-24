package com.phs.minidms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView home() {
        // → /WEB-INF/views/home.jsp
        ModelAndView mv = new ModelAndView("Home");
        mv.addObject("message", "Hello, MiniDMS!");
        return mv;
    }
}