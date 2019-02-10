package com.azino.project.service.impl;

import com.azino.project.model.Role;
import com.azino.project.repository.RoleRepository;
import com.azino.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleRepository> implements RoleService {
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        super(roleRepository);
    }
}
