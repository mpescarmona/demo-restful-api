package com.mpescarmona.demorestfulapi.controller;

import com.mpescarmona.demorestfulapi.domain.User;
import com.mpescarmona.demorestfulapi.exception.UserAlreadyRegisteredException;
import com.mpescarmona.demorestfulapi.exception.UserNotFoundException;
import com.mpescarmona.demorestfulapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.Optional;
import java.util.UUID;

@Api(value = "Register Controller")
@Slf4j
@RestController
@Validated
@RequestMapping("/demo-api/user")
public class RegisterController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Registers a new user")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User registered successfully")})
    @PostMapping(path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerNewUser(@RequestBody @Valid User user) {
        log.info("action=registerNewUser");
        Optional<User> optionalUser = userService.findUserByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            throw new UserAlreadyRegisteredException(user.getEmail());
        }

        user.setToken(UUID.randomUUID().toString());

        User newUser = userService.registerUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets a registered user by email")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "User successfully retrieved")})
    @GetMapping(path = "/{email}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findUserByEmail(@PathVariable @Email(message = "Email should be valid") String email) {
        log.info("action=findUserByEmail");
        User user = userService.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
