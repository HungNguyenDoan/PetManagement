package com.project.petmanagement.petmanagement.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.petmanagement.petmanagement.models.Role;
import com.project.petmanagement.petmanagement.repositories.RoleRepository;


@Component
public class RoleSeeder {
    @Autowired
    private RoleRepository roleRepository;

    public void seed() {
        Role role = Role.builder()
                .id(1L)
                .roleName("USER")
                .build();
        roleRepository.save(role);
    }
}
