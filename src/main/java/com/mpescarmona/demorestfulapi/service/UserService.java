package com.mpescarmona.demorestfulapi.service;

import com.mpescarmona.demorestfulapi.domain.Phone;
import com.mpescarmona.demorestfulapi.domain.User;
import com.mpescarmona.demorestfulapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listUsers() {
        log.info("action=listUsers");
        return userRepository.findAll();
    }

    public Optional<User> findUser(String userId) {
        log.info("action=findUser, userId={}", userId);
        return userRepository.findById(userId);
    }

    public Optional<User> findUserByEmail(String email) {
        log.info("action=findUserByEmail, email={}", email);
        return userRepository.findByEmail(email);
    }

    public User registerUser(User user) {
        log.info("action=registerUser, user={}", user);
        Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        user.setCreated(currentDate);
        user.setUpdated(currentDate);
        user.setLastLogin(currentDate);
        user.setActive(true);

        List<Phone> phones = user.getPhones();
        user.setPhones(null);

        User savedUser = userRepository.save(user);

        for (Phone phone : phones) {
            phone.setUser(savedUser);
        }
        savedUser.setPhones(phones);
        userRepository.save(savedUser);

        log.info("action=registerUser, user={} registered", savedUser);
        return user;
    }
}
