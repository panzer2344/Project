package com.azino.project.service;

import com.azino.project.model.User;

public interface UserService extends BaseService<User> {
    User findByName(String userName);

    User deleteByName(String userName);
    User fromUserDetailsUser(org.springframework.security.core.userdetails.User user);
}
