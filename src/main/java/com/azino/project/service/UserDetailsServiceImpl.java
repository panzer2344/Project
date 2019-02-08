package com.azino.project.service;

import com.azino.project.model.Role;
import com.azino.project.model.User;
import com.azino.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);

        if(user == null){
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        System.out.println("Found User : " + user);

        Set<Role> roles = user.getRoles();
        //List<Role> roles = user.getRoles();

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        if(roles != null){
            for(Role role : roles){
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                grantedAuthorityList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                grantedAuthorityList
        );

        return userDetails;
    }
}
