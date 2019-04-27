package com.group3.basic.netcracker.backend.service.impl;

import com.group3.basic.netcracker.backend.dao.UserDao;
import com.group3.basic.netcracker.backend.entity.User;
import com.group3.basic.netcracker.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public void createUser(String username, String role, String fname, String lname,
                           String email, String pass, LocalDate created_at, byte[] photo) {
        userDao.createUser(username, role, fname, lname, email, pass, created_at, photo);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public List listUsers() {
        return userDao.listUsers();
    }

    @Override
    public List getTrainers() {
        return userDao.getTrainers();
    }


    @Override
    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    @Override
    public void updateUser(int id, String username, String  role, String fname, String lname,
                           String email, String pass, LocalDate created_at, byte[] photo) {
        userDao.updateUser(id, username, role, fname, lname, email, pass, created_at, photo);
    }

    @Override
    public void addMember(String username, String role, String fname, String lname, String email, String pass, LocalDate created_at) {
        userDao.addMember(username, role, fname, lname, email, pass, created_at);
    }

    @Override
    public void updateUserName(int id, String newUsername) {
        userDao.updateUserName(id, newUsername);
    }

    @Override
    public void updateUserFirstName(int id, String newFirstName) {
        userDao.updateUserFirstName(id, newFirstName);
    }

    @Override
    public void updateUserLastName(int id, String newLastName) {
        userDao.updateUserLastName(id, newLastName);
    }

    @Override
    public void updateUserEmail(int id, String newEmail) {
        userDao.updateUserEmail(id, newEmail);
    }

    @Override
    public boolean isUserExists(String username, String email) {
        return userDao.isUserExists(username, email);
    }

    @Override
    public List listUsersForDisplay(String role) {
        return userDao.listUsersForDisplay(role);
    }
}
