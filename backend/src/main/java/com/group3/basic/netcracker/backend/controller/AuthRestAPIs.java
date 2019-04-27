package com.group3.basic.netcracker.backend.controller;

import java.time.LocalDate;

import javax.validation.Valid;


import com.group3.basic.netcracker.backend.dao.impl.UserDaoImpl;
import com.group3.basic.netcracker.backend.util.authorization.message.request.LoginForm;
import com.group3.basic.netcracker.backend.util.authorization.message.request.SignUpForm;
import com.group3.basic.netcracker.backend.util.authorization.message.response.JwtResponse;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import com.group3.basic.netcracker.backend.util.authorization.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;

    private final JwtProvider jwtProvider;

    private ApplicationContext context;

    @Autowired
    public AuthRestAPIs(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                        JwtProvider jwtProvider, ApplicationContext context){
        this.authenticationManager = authenticationManager;
        this.encoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.context = context;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));

        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username or Password is wrong!"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        UserDaoImpl jdbcTemplateUsersDao = context.getBean(UserDaoImpl.class);
        if (jdbcTemplateUsersDao.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        jdbcTemplateUsersDao.createUser(signUpRequest.getUsername(),
                "Admin",signUpRequest.getFname(),signUpRequest.getLname(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), LocalDate.now(), null);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }
}
