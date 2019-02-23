package com.azino.project.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;

import java.util.Collection;

public class WebUtils {

    public static String toString(User user) {
        StringBuilder sb = new StringBuilder();

        sb.append("UserName:").append(user.getUsername());

        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            sb.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    sb.append(a.getAuthority());
                    first = false;
                } else {
                    sb.append(", ").append(a.getAuthority());
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }

    public static StringBuilder bindingResultErrorsToString(BindingResult bindingResult){
        StringBuilder error = new StringBuilder();
        bindingResult.getFieldErrors().forEach(
                fieldError -> error
                        .append(fieldError.getField())
                        .append(" ")
                        .append(fieldError.getDefaultMessage())
                        .append("\n")
        );
        return error;
    }
}
