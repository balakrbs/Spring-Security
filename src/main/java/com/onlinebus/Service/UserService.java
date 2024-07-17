package com.onlinebus.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlinebus.Model.Users;
import com.onlinebus.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void save(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
    
    public Optional<Users> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
