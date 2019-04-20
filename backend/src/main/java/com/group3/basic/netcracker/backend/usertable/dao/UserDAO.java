package com.group3.basic.netcracker.backend.usertable.dao;

import com.group3.basic.netcracker.backend.usertable.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserDAO {

    void createUser(String username, String  role, String fname, String lname,
                    String email, String pass, LocalDate created_at, byte[] photo);

    User getUserById(int id);

    List listUsers();

    void removeUser(int id);

    void updateUser(String username, String  role, String fname, String lname,
                    String email, String pass, LocalDate created_at, byte[] photo);
}
