package com.mpescarmona.demorestfulapi.service;

import com.mpescarmona.demorestfulapi.domain.Phone;
import com.mpescarmona.demorestfulapi.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    public void whenApplicationStarts_thenHibernateShouldReturnUserByEmail() {
        Optional<User> optionalUser = userService.findUserByEmail("john.doe@testmail.com");

        assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@testmail.com", user.getEmail());
        assertEquals("j0hnd03s3cr3t", user.getPassword());
        assertTrue(user.isActive());
    }

    @Test
    public void whenApplicationStarts_thenHibernateShouldRegisterNewUser() {
        Phone phone1 = Phone.builder()
                .number(1111)
                .countrycode(2222)
                .citycode(3333)
                .build();
        Phone phone2 = Phone.builder()
                .number(4444)
                .countrycode(5555)
                .citycode(6666)
                .build();
        User newUser = User.builder()
                .name("testName")
                .email("testEmail@email.com")
                .password("testPassword01")
                .phones(Arrays.asList(phone1, phone2))
                .build();

        User persistedUser = userService.registerUser(newUser);

        assertNotNull(persistedUser.getUserId());
        assertEquals(newUser.getName(), persistedUser.getName());
        assertEquals(newUser.getEmail(), persistedUser.getEmail());
        assertEquals(newUser.getPassword(), persistedUser.getPassword());
        assertNotNull(persistedUser.getCreated());
        assertNotNull(persistedUser.getLastLogin());
        assertNotNull(persistedUser.getUpdated());
        assertTrue(persistedUser.isActive());
        assertEquals(2, persistedUser.getPhones().size());
        assertEquals(phone1.getNumber(), persistedUser.getPhones().get(0).getNumber());
        assertEquals(phone2.getNumber(), persistedUser.getPhones().get(1).getNumber());
    }

    @Test
    public void whenApplicationStarts_thenHibernateShouldReturnOnePhoneForUserOne() {
        Optional<User> optionalUser = userService.findUser("User0001-1111-1111-1111-111111111111");

        assertTrue(optionalUser.isPresent());

        User user = optionalUser.get();
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@testmail.com", user.getEmail());
        assertEquals("j0hnd03s3cr3t", user.getPassword());
        assertTrue(user.isActive());

        assertEquals(1, user.getPhones().size());
        assertEquals(user, user.getPhones().get(0).getUser());
        assertEquals("Phone001-1111-1111-1111-111111111111", user.getPhones().get(0).getPhoneId());
        assertEquals(1, user.getPhones().get(0).getCitycode());
        assertEquals(56, user.getPhones().get(0).getCountrycode());
        assertEquals(12345678, user.getPhones().get(0).getNumber());
    }
}