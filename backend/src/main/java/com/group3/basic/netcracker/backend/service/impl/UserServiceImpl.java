package com.group3.basic.netcracker.backend.service.impl;

import com.group3.basic.netcracker.backend.dao.UserDao;
import com.group3.basic.netcracker.backend.dto.UserForDisplay;
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
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(String username, String role, String fname, String lname,
                           String email, String pass, LocalDate created_at, String photo) {
        userDao.createUser(username, role, fname, lname, email, pass, created_at, photo, userDao.getFreeManager());
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public Integer getIdByUsername(String username) {
        return userDao.getIdByUsername(username);
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
    public void resetPassword(String email, String pass) {
         userDao.resetPassword(email, pass);
    }


    @Override
    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    @Override
    public void updateUser(int id, String username, String fname, String lname,
                           String email) {
        userDao.updateUser(id, username, fname, lname, email);
    }

    @Override
    public void updateTrainerInfo(int id, String info) {
        userDao.updateTrainerInfo(id, info);
    }

    @Override
    public void updateName(int id, String fname, String lname) {
        userDao.updateName(id, fname, lname);
    }

    @Override
    public void addMember(String username, String role, String fname, String lname, String email,
                          String pass, LocalDate created_at) {
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

    @Override
    public List getStudentsOfTrainer(String username) {
        return userDao.getStudentsOfTrainer(username);
    }

    @Override
    public List getStudentsOfManager(String username) {
        return userDao.getStudentsOfManager(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    public boolean existByUsername(String email) {
        return userDao.existsByUsername(email);
    }

    @Override
    public List getManagerOfStudent(String username) {
        return userDao.getManagerOfStudent(username);
    }

    @Override
    public List getTrainersInfo() {
        return userDao.getTrainersInfo();
    }

    @Override
    public List<String> getTrainerCourses(int id) {
        return userDao.getTrainerCourses(id);
    }

    @Override
    public UserForDisplay getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public List getStudentsByCourseName(String course) {
        return userDao.getStudentsByCourseName(course);
    }

    @Override
    public void insertTrainerInfo(int id, String info) {
        userDao.insertTrainerInfo(id, info);
    }

    @Override
    public int getId(String username) {
        return userDao.getId(username);
    }

    @Override
    public int updatePhoto(String username, String filepath) {
        return userDao.updatePhoto(username, filepath);
    }

    @Override
    public String getPhotoByUsername(String username) {
        return userDao.getPhotoByUsername(username);
    }
}
