package com.azino.project.service.impl;

import com.azino.project.model.DTO.form.FormUser;
import com.azino.project.model.User;
import com.azino.project.repository.UserRepository;
import com.azino.project.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserRepository> implements UserService {

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    public User findByName(String userName) {
        return super.repository.findByName(userName);
    }

    @Override
    public User deleteByName(String userName) {
        return super.repository.deleteByName(userName);
    }

    @Override
    public User fromUserDetailsUser(org.springframework.security.core.userdetails.User user) {
        if (user == null) {
            return null;
        } else {
            return findByName(user.getUsername());
        }
    }

    @Override
    public User fromFormUser(FormUser formUser) {
        return new User(
                formUser.getName(),
                formUser.getPassword(),
                formUser.getFirstName(),
                formUser.getLastName(),
                formUser.getAge(),
                formUser.getRoles()
        );
    }
}
