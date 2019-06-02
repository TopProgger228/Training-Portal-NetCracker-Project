package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.dto.UserForDisplay;
import com.group3.basic.netcracker.backend.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    void createUser(String username, String role, String fname, String lname,
                    String email, String pass, LocalDate created_at, String photo);

    User getUserById(int id);

    Integer getIdByUsername(String username);

    List listUsers();

    List getTrainers();

    void resetPassword(String email, String pass);

    void removeUser(int id);

    void updateUser(int id, String username, String fname, String lname, String email);

    void updateTrainerInfo(int id, String info);

    void updateName(int id, String fname, String lname);

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

    boolean existByUsername(String email);

    List getManagerOfStudent(String username);

    List getTrainersInfo();

    List<String> getTrainerCourses(int id);

    UserForDisplay getUserByUsername(String username);

    List getStudentsByCourseName(String course);

    void insertTrainerInfo(int id, String info);

    int getId(String username);

    int updatePhoto(String username, String filepath);

    String getPhotoByUsername(String username);


}
