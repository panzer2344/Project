package com.azino.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TestController {

    @GetMapping("usersTest")
    public ModelAndView usersTest(){
        return new ModelAndView("users/usersTest");
    }

    @GetMapping("BStest")
    public ModelAndView bsTest(){
        return new ModelAndView("BStest");
    }
}
