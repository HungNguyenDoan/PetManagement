package com.project.petmanagement.petmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.petmanagement.petmanagement.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhonenumber(String username);
}
