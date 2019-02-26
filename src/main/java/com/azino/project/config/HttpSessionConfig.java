package com.azino.project.config;

import com.azino.project.security.listeners.JHttpSessionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSessionListener;

@Configuration
public class HttpSessionConfig {

    @Bean
    public HttpSessionListener httpSessionListener(){
        return new JHttpSessionListener();
    }

}
