package com.azino.project.service;

import com.azino.project.model.User;
import com.azino.project.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, UserRepository> {

    public UserService(UserRepository userRepository){
        super(userRepository);
    }
}
