package com.azino.project.service.impl;

import com.azino.project.model.DTO.form.FormUser;
import com.azino.project.model.User;
import com.azino.project.repository.UserRepository;
import com.azino.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserRepository> implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @Override
    public boolean isValid(String userName, String password) {
        User user = findByName(userName);
        if(null != user) {
            if (bCryptPasswordEncoder.matches(password, user.getPassword())){
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public List<String> getAllActiveSessionsFromUser(String userName) {
        List<String> sessions = null;
        User user = findByName(userName);
        if(null != user){
            sessions = user.getActiveSessions();
        }
        return sessions;
    }

    @Override
    @Transactional
    public boolean isSessionInActiveSessions(String userName, String sessionId) {
        boolean isIn = false;
        User user = findByName(userName);
        if(null != user){
            List<String> sessions = user.getActiveSessions();
            if(null != sessions) {
                isIn = sessions.contains(sessionId);
            }
        }
        return isIn;
    }

    @Override
    @Transactional
    public boolean deleteSessionFromActiveSessions(String userName, String sessionId) {
        boolean isDeleted = false;
        User user = findByName(userName);
        if(null != user){
            List<String> sessions = user.getActiveSessions();
            if(null != sessions) {
                if (sessions.contains(sessionId)) {
                    sessions.remove(sessionId);
                    isDeleted = true;
                }
            }
        }
        return isDeleted;
    }

    @Override
    @Transactional
    public User addSessionToActiveSessions(String userName, String sessionId) {
        User user = findByName(userName);
        if(null != user){
            List<String> sessions = user.getActiveSessions();
            if(null == sessions){
                user.setActiveSessions(new ArrayList<>());
                sessions = user.getActiveSessions();
            }
            if(!sessions.contains(sessionId)){
                sessions.add(sessionId);
            }
        }
        return user;
    }

    @Override
    @Transactional
    public List<String> getAllActiveSessions() {
        Iterable<User> userList = findAll();
        List<String> list = new ArrayList<>();
        userList.forEach(u -> list.addAll(u.getActiveSessions()));
        return list;
    }

    @Override
    @Transactional
    public void deleteAllActiveSessions() {
        Iterable<User> userList = findAll();
        userList.forEach(u -> u.getActiveSessions().removeAll(u.getActiveSessions()));
    }
}
