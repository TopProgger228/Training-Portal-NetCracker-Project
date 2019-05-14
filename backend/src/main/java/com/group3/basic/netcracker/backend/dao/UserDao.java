package com.group3.basic.netcracker.backend.dao;

import com.group3.basic.netcracker.backend.dto.UserForDisplay;
import com.group3.basic.netcracker.backend.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserDao {

    void createUser(String username, String  role, String fname, String lname,
                    String email, String pass, LocalDate created_at, byte[] photo);


    void resetPassword(String email, String pass);

    User getUserById(int id);

    Integer getIdByUsername(String username);

    List listUsers();

    List getTrainers();

    void removeUser(int id);

    void updateUser(int id, String username, String  role, String fname, String lname,
                    String email, String pass, LocalDate created_at, byte[] photo);

    void updateName(int id, String fname, String lname);

    void updateTrainerInfo(int id, String info);

    List listUsersForDisplay(String role);

    void addMember(String username, String role, String fname,
                   String lname, String email, String pass, LocalDate created_at);

    void updateUserName(int id, String newUsername);


    void updateUserFirstName(int id, String newFirstName);

    void updateUserLastName(int id, String newLastName);

    void updateUserEmail(int id, String newEmail);

    boolean isUserExists(String username, String email);

    boolean existsByEmail(String email);

    List<User> getUsersByLesson (int lessonId);

    User getTrainerByCourse (int courseId);

    List getStudentsOfTrainer (String username);

    List<User> getStudentsOfManager (String username);

    List getManagerOfStudent(String username);

    UserForDisplay getUserByUsername(String username);

    List getTrainersInfo();

    List<String> getTrainerCourses(int id);

    List getStudentsByCourseName(String course);

    void insertTrainerInfo(int id, String info);

    int getId(String username);
}
