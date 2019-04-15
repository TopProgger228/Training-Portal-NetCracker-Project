package com.group3.basic.netcracker.backend.UsersTable.controller;

import com.group3.basic.netcracker.backend.UsersTable.model.Users;
import com.group3.basic.netcracker.backend.UsersTable.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    UsersRepository repository;

    @GetMapping("/users")
    public List<Users> getAllUsers() {
        System.out.println("Get all Users...");

        List<Users> users = new ArrayList<>();
        repository.findAll().forEach(users::add);

        return users;
    }

    @PostMapping(value = "/users/create")
    public Users postUsers(@RequestBody Users users) {

        Users _users = repository.save(new Users(users.getUsername(), users.getFname(), users.getLname(),
                users.getEmail(), users.getPass(), users.getPhoto()));
        return _users;
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUsers(@PathVariable("id") long id) {
        System.out.println("Delete User with ID = " + id + "...");

        repository.deleteById(id);

        return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/users/delete")
    public ResponseEntity<String> deleteAllUsers() {
        System.out.println("Delete All Users...");

        repository.deleteAll();

        return new ResponseEntity<>("All users have been deleted!", HttpStatus.OK);
    }
/*
    @GetMapping(value = "users/id/{id}")
    public List<Users> findById(@PathVariable long id) {

        List<Users> users = repository.findById(id);
        return users;
    }
*/
    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUsers(@PathVariable("id") long id, @RequestBody Users users) {
        System.out.println("Update Users with ID = " + id + "...");

        Optional<Users> usersData = repository.findById(id);

        if (usersData.isPresent()) {
            Users _users = usersData.get();
            _users.setUsername(users.getUsername());
            _users.setFname(users.getFname());
            _users.setLname(users.getLname());
            _users.setEmail(users.getEmail());
            _users.setPass(users.getPass());
            _users.setPhoto(users.getPhoto());
            return new ResponseEntity<>(repository.save(_users), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
