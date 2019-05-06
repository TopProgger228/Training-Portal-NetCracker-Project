package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    void createUser(String username, String  role, String fname, String lname,
                    String email, String pass, LocalDate created_at, byte[] photo);

    User getUserById(int id);

    List listUsers();

    List getTrainers();

    void removeUser(int id);

    void updateUser(int id, String username, String role, String fname, String lname,
                    String email, String pass, LocalDate created_at, byte[] photo);

    void addMember(String username, String role, String fname, String lname,
                   String email, String pass, LocalDate created_at);

    void updateUserName(int id, String newUsername);

    void updateUserFirstName(int id, String newFirstName);

    void updateUserLastName(int id, String newLastName);

    void updateUserEmail(int id, String newEmail);

    boolean isUserExists(String username, String email);

    List listUsersForDisplay(String role);

    List getStudentsOfTrainer(String username);

    List getStudentsOfManager(String username);

    boolean existByEmail(String email);

    List getManagerOfStudent(String username);

}
