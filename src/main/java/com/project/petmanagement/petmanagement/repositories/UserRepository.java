package com.project.petmanagement.petmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.petmanagement.petmanagement.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
