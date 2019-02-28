package com.azino.project.events.listeners;

import com.azino.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener {

    @Autowired
    private UserService userService;

    @EventListener(ApplicationReadyEvent.class)
    public void appReadyListener(){
        deleteAllFromUserActiveSessions();
    }


    public void deleteAllFromUserActiveSessions(){
        userService.deleteAllActiveSessions();
    }
}
