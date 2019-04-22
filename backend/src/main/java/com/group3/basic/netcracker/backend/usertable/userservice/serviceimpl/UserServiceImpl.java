package com.group3.basic.netcracker.backend.usertable.userservice.serviceimpl;

import com.group3.basic.netcracker.backend.usertable.dao.UserDAO;
import com.group3.basic.netcracker.backend.usertable.entity.User;
import com.group3.basic.netcracker.backend.usertable.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public void createUser(String username, String role, String fname, String lname,
                           String email, String pass, LocalDate created_at, byte[] photo) {
        userDAO.createUser(username, role, fname, lname, email, pass, created_at, photo);
    }

    @Override
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Override
    public List listUsers() {
        return userDAO.listUsers();
    }

    @Override
    public void removeUser(int id) {
        userDAO.removeUser(id);
    }

    @Override
    public void updateUser(int id, String username, String  role, String fname, String lname,
                           String email, String pass, LocalDate created_at, byte[] photo) {
        userDAO.updateUser(id, username, role, fname, lname, email, pass, created_at, photo);
    }

    @Override
    public void addMember(String username, String role, String fname, String lname, String email, String pass, LocalDate created_at) {
        userDAO.addMember(username, role, fname, lname, email, pass, created_at);
    }
}
