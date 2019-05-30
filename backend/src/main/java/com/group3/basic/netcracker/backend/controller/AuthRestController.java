package com.group3.basic.netcracker.backend.controller;

import java.time.LocalDate;
import javax.validation.Valid;


import com.group3.basic.netcracker.backend.service.UserService;
import com.group3.basic.netcracker.backend.util.authorization.message.request.LoginForm;
import com.group3.basic.netcracker.backend.util.authorization.message.request.SignUpForm;
import com.group3.basic.netcracker.backend.util.authorization.message.response.JwtResponse;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import com.group3.basic.netcracker.backend.util.authorization.security.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthRestController {
    private static final String STUDENT_ROLE = "Student";

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;

    private final JwtProvider jwtProvider;

    private UserService userService;

    @Autowired
    public AuthRestController(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                              JwtProvider jwtProvider, UserService userService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.encoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));


            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtProvider.generateJwtToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            log.info("User authenticated!");

            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));

        } catch (Exception e) {
            log.warn("Wrong user credentials!");
            return new ResponseEntity<>(new ResponseMessage("Username or Password is wrong!"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm info) {
        if (userService.existByUsername(info.getUsername())) {
            log.info("Username - {} is already taken", info.getUsername());
            return new ResponseEntity<>(new ResponseMessage("username is already taken"),
                    HttpStatus.OK);
        }else if (userService.existByEmail(info.getEmail())) {
            log.info("Email - {} is already taken", info.getEmail());
            return new ResponseEntity<>(new ResponseMessage("email is already taken"),
                    HttpStatus.OK);
        }
        userService.createUser(info.getUsername(),
                STUDENT_ROLE, info.getFname(), info.getLname(), info.getEmail(),
                encoder.encode(info.getPassword()), LocalDate.now(), null);

        log.info("User registered with username - {}", info.getUsername());

        return new ResponseEntity<>(new ResponseMessage("registered successfully"), HttpStatus.OK);
    }

    @PostMapping("/reset_password")
    public ResponseEntity<?> resetPassword(@RequestParam("email") String email,
                                           @RequestParam("password") String password) {
        if (!userService.existByEmail(email)) {
            log.info("User with email - {} does not exists", email);
            return new ResponseEntity<>(new ResponseMessage("user does not exist"),
                    HttpStatus.OK);
        }
        userService.resetPassword(email, encoder.encode(password));

        log.info("User with email - {} reseted password successfully", email);

        return new ResponseEntity<>(new ResponseMessage("successfully"), HttpStatus.OK);
    }
}
