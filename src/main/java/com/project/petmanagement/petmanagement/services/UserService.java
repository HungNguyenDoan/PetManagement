package com.project.petmanagement.petmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.petmanagement.petmanagement.JWT.JWTUserDetail;
import com.project.petmanagement.petmanagement.models.User;
import com.project.petmanagement.petmanagement.models.Role;
import com.project.petmanagement.petmanagement.payloads.requests.RegisterRequest;
import com.project.petmanagement.petmanagement.repositories.RoleRepository;
import com.project.petmanagement.petmanagement.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPhonenumber(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new JWTUserDetail(user);
    }

    public void register(RegisterRequest request) {
        Role userRole = roleRepository.getReferenceById(1L);
        User user = User.builder()
                .fullname(request.getFullName())
                .dob(request.getDob())
                .email(request.getEmail())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .address(request.getAddress())
                .phonenumber(request.getPhonenumber())
                .role(userRole)
                .build();
        userRepository.save(user);
    }
}
