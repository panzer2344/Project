package com.azino.project.service;

import com.azino.project.model.User;
import com.azino.project.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserRepository> implements UserService {

    public UserServiceImpl(UserRepository userRepository){
        super(userRepository);
    }
}
