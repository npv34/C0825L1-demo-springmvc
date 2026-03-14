package com.codegym.springmvc.services;

import com.codegym.springmvc.models.Role;
import com.codegym.springmvc.repositories.IRoleRepository;
import com.codegym.springmvc.request.RoleRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleRequest> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleRequest> roleRequests = new ArrayList<>();
        for (Role role : roles) {
            RoleRequest roleRequest = new RoleRequest(role.getId().intValue(), role.getName());
            roleRequests.add(roleRequest);
        }
        return roleRequests;
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
