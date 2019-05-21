package com.group3.basic.netcracker.backend.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import javax.imageio.ImageIO;
import javax.validation.Valid;


import com.group3.basic.netcracker.backend.dao.impl.UserDaoImpl;
import com.group3.basic.netcracker.backend.service.UsersTokenService;
import com.group3.basic.netcracker.backend.util.authorization.message.request.LoginForm;
import com.group3.basic.netcracker.backend.util.authorization.message.request.SignUpForm;
import com.group3.basic.netcracker.backend.util.authorization.message.response.JwtResponse;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import com.group3.basic.netcracker.backend.util.authorization.security.jwt.JwtProvider;
import com.group3.basic.netcracker.backend.util.file.ImageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;

    private final JwtProvider jwtProvider;

    private ApplicationContext context;

    private UsersTokenService usersTokenService;


    @Autowired
    public AuthRestAPIs(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                        JwtProvider jwtProvider, ApplicationContext context, UsersTokenService usersTokenService) {
        this.authenticationManager = authenticationManager;
        this.encoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.context = context;
        this.usersTokenService = usersTokenService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));


            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtProvider.generateJwtToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username or Password is wrong!"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm info) {
        UserDaoImpl jdbcTemplateUsersDao = context.getBean(UserDaoImpl.class);
        if (jdbcTemplateUsersDao.existsByUsername(info.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        jdbcTemplateUsersDao.createUser(info.getUsername(),
                "Student", info.getFname(), info.getLname(), info.getEmail(),
                encoder.encode(info.getPassword()), LocalDate.now(),null);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

    @PostMapping("/reset_password")
    public ResponseEntity<?> resetPassword(@RequestParam("email") String email, @RequestParam("password") String password) {
        UserDaoImpl jdbcTemplateUsersDao = context.getBean(UserDaoImpl.class);
        if (!jdbcTemplateUsersDao.existsByEmail(email)) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> User does not exist!"),
                    HttpStatus.BAD_REQUEST);
        }
        jdbcTemplateUsersDao.resetPassword(email, encoder.encode(password));

        return new ResponseEntity<>(new ResponseMessage("User password reset successfully!"), HttpStatus.OK);
    }
}
