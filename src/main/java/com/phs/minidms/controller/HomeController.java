package com.phs.minidms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class HomeController {

//    @GetMapping("/")
//    public ModelAndView home() {
//        // → /WEB-INF/views/home.jsp
//        ModelAndView mv = new ModelAndView("home");
//        mv.addObject("message", "Hello, MiniDMS!");
//        return mv;
//    }
@GetMapping("/")
public String home() {
    return "home"; // /WEB-INF/views/home.jsp
}
}