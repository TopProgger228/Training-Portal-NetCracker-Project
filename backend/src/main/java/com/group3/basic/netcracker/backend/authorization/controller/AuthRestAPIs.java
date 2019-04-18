package com.group3.basic.netcracker.backend.authorization.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import com.group3.basic.netcracker.backend.UserTable.dao.jdbc.JdbcTemplateUserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

import com.group3.basic.netcracker.backend.authorization.message.request.LoginForm;
import com.group3.basic.netcracker.backend.authorization.message.request.SignUpForm;
import com.group3.basic.netcracker.backend.authorization.message.response.JwtResponse;
import com.group3.basic.netcracker.backend.authorization.message.response.ResponseMessage;
//import com.group3.basic.netcracker.backend.authorization.model.Role;
import com.group3.basic.netcracker.backend.authorization.security.jwt.JwtProvider;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbctemplate-user-config.xml");

        JdbcTemplateUserDaoImpl jdbcTemplateUsersDao =
                (JdbcTemplateUserDaoImpl) context.getBean("jdbcTemplateUsersDao");
        if (jdbcTemplateUsersDao.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }



        jdbcTemplateUsersDao.createUser(signUpRequest.getUsername(), 4,signUpRequest.getFname(),signUpRequest.getLname(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), LocalDate.now(), null);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }
}
