package com.mpescarmona.demorestfulapi.repository;

import com.mpescarmona.demorestfulapi.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {

        User testUser = User.builder()
                .email("testEmail@email.com")
                .name("userName_1")
                .build();
        entityManager.persist(testUser);

        Optional<User> retrievedUser = userRepository.findByEmail("testEmail@email.com");

        assertTrue(retrievedUser.isPresent());
        assertEquals(testUser.getName(), retrievedUser.get().getName());
    }
}