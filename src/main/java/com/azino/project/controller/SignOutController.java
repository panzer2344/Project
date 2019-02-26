package com.azino.project.controller;

import com.azino.project.service.SignOutService;
import com.azino.project.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("signOut")
public class SignOutController {

    @Autowired
    private SignOutService signOutService;

    @Autowired
    private UserUtils userUtils;

    @GetMapping
    public ModelAndView signOut(Principal principal, HttpSession session){
        if(principal != null) {
            signOutService.signOut(
                    userUtils.fromPrincipal(principal).getName(),
                    session
            );
        }
        return new ModelAndView("redirect:/signOutSecurity");
    }

}
