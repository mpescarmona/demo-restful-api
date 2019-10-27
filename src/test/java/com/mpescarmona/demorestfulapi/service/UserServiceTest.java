package com.mpescarmona.demorestfulapi.service;

import com.mpescarmona.demorestfulapi.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<User> users = userService.listUsers();

        assertEquals(3, users.size());
    }

    @Test
    public void whenApplicationStarts_thenHibernateShouldReturnOnePhoneForUseOne() {
        Optional<User> optionalUser = userService.finUser(Long.valueOf(1));

        assertTrue(optionalUser.isPresent());

        User user = optionalUser.get();
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@testmail.com", user.getEmail());
        assertEquals("j0hnd03s3cr3t", user.getPassword());

        assertEquals(1, user.getPhones().size());
        assertEquals(user, user.getPhones().get(0).getUser());
        assertEquals(1, user.getPhones().get(0).getPhoneId());
        assertEquals(1, user.getPhones().get(0).getCitycode());
        assertEquals(56, user.getPhones().get(0).getCountrycode());
        assertEquals(12345678, user.getPhones().get(0).getNumber());
    }
}