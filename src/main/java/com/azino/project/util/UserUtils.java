package com.azino.project.util;

import com.azino.project.model.User;
import com.azino.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class UserUtils {

    @Autowired
    private UserService userService;

    public User fromPrincipal(Principal principal){
        return userService.fromUserDetailsUser(
                (org.springframework.security.core.userdetails.User) ((Authentication)principal).getPrincipal()
        );
    }

}
