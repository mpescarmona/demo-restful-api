package com.mpescarmona.demorestfulapi.service;

import com.mpescarmona.demorestfulapi.domain.User;
import com.mpescarmona.demorestfulapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public Optional<User> finUser(Long userId) {
        return userRepository.findById(userId);
    }
}
