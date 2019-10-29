package com.mpescarmona.demorestfulapi.controller;

import com.mpescarmona.demorestfulapi.domain.User;
import com.mpescarmona.demorestfulapi.domain.response.ErrorMessage;
import com.mpescarmona.demorestfulapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/demo-api")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerNewUser(@RequestBody @Valid User user) {
        log.info("action=registerNewUser");
        Optional<User> optionalUser = userService.findUserByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            log.info("action=registerNewUser, The provided email is already registered={}", user.getEmail());
            ErrorMessage emailAlredyExistsMessage = ErrorMessage.builder()
                    .message("The provided email '" + user.getEmail() + "' is already registered")
                    .build();
            return new ResponseEntity<>(emailAlredyExistsMessage, HttpStatus.PRECONDITION_FAILED);
        }
        User newUser = userService.registerUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
}
