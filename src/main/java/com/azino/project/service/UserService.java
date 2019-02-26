package com.azino.project.service;

import com.azino.project.model.DTO.form.FormUser;
import com.azino.project.model.User;

import java.util.List;

public interface UserService extends BaseService<User> {
    User findByName(String userName);

    User deleteByName(String userName);
    User fromUserDetailsUser(org.springframework.security.core.userdetails.User user);

    User fromFormUser(FormUser formUser);

    boolean isValid(String userName, String password);

    List<String> getAllActiveSessions(String userName);

    boolean isSessionInActiveSessions(String userName, String sessionId);

    boolean deleteSessionFromActiveSessions(String userName, String sessionId);

    User addSessionToActiveSessions(String userName, String sessionId);

}
