package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.UserDao;
import com.group3.basic.netcracker.backend.dto.UserForDisplay;
import com.group3.basic.netcracker.backend.entity.User;
import com.group3.basic.netcracker.backend.util.dtomapper.CourseAttendeeMapper;
import com.group3.basic.netcracker.backend.util.dtomapper.TrainersInfoDtoMapper;
import com.group3.basic.netcracker.backend.util.rowmapper.*;
import com.group3.basic.netcracker.backend.util.sql.UserDaoQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void resetPassword(String email, String pass) {
        jdbcTemplate.update(UserDaoQueries.resetPasswordQuery, pass, email);
    }

    @Override
    public User getUserById(int id) {
        User user = (User) jdbcTemplate.queryForObject(UserDaoQueries.getUserByIdQuery,
                new Object[]{id}, new UserRowMapper());
        return user;
    }

    @Override
    public Integer getIdByUsername(String username) {
        User userId = (User) jdbcTemplate.queryForObject(UserDaoQueries.getIdByUsernameQuery,
                new Object[]{username}, new UserIdRowMapper());
        return userId.getId();
    }

    @Override
    public UserForDisplay getUserByUsername(String username) {
        return (UserForDisplay) jdbcTemplate.query(UserDaoQueries.getUserByUsernameQuery,
                new UserProfileRowMapper(), username).get(0);
    }


    public boolean existsByUsername(String username) {
        Long count = (Long) jdbcTemplate.queryForObject(UserDaoQueries.existsByUsernameQuery,
                new Object[]{username}, Object.class);

        return count != 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        Long count = (Long) jdbcTemplate.queryForObject(UserDaoQueries.existsByEmailQuery,
                new Object[]{email}, Object.class);

        return count != 0;
    }


    public User findByUsername(String username) {
        return (User) jdbcTemplate.queryForObject(UserDaoQueries.findByUsernameQuery,
                new Object[]{username}, new UserRowMapper());
    }

    @Override
    public List listUsers() {
        return jdbcTemplate.query(UserDaoQueries.usersListQuery, new UserRowMapper());
    }

    @Override
    public List getTrainers() {
        return jdbcTemplate.query(UserDaoQueries.getTrainersQuery, new TrainerRowMapper());
    }

    @Override
    public List listUsersForDisplay(String role) {
        return jdbcTemplate.query(UserDaoQueries.getUsersListForDisplay,
                new UserForDisplayRowMapper(), role);
    }

    @Override
    public List<User> getStudentsOfManager(String username) {
        return jdbcTemplate.query(UserDaoQueries.getStudentsOfManagerQuery, new UserRowMapper(), username);
    }

    @Override
    public List getManagerOfStudent(String username) {
        return jdbcTemplate.query(UserDaoQueries.getManagerOfStudent, new UserRowMapper(), username);
    }

    @Override
    public void removeUser(int id) {
        jdbcTemplate.update(UserDaoQueries.removeUserQuery, id);
    }


    @Override
    public void createUser(String username, String role, String fname, String lname, String email, String pass,
                           LocalDate created_at, String photo) {

        jdbcTemplate.update(UserDaoQueries.createUserQuery, username, role, fname, lname,
                email, pass, created_at, photo);
    }

    @Override
    public void updateUser(int id, String username, String fname, String lname, String email) {
        jdbcTemplate.update(UserDaoQueries.updateUserQuery, username, fname, lname, email, id);
    }

    @Override
    public int updatePhoto(String username, String filepath) {
        return jdbcTemplate.update(UserDaoQueries.updatePhotoQuery, filepath, username);
    }

    @Override
    public String getPhotoByUsername(String username) {
        return jdbcTemplate.queryForObject(UserDaoQueries.getPhotoByUsername, String.class, username);
    }

    @Override
    public void updateName(int id, String fname, String lname) {
        jdbcTemplate.update(UserDaoQueries.updateName, fname, lname, id);
    }

    @Override
    public void updateTrainerInfo(int id, String info) {
        jdbcTemplate.update(UserDaoQueries.updateTrainersInfo, info, id);
    }

    @Override
    public void addMember(String username, String role, String fname, String lname, String email, String pass,
                          LocalDate created_at) {
        jdbcTemplate.update(UserDaoQueries.addMemberQuery, username, role, fname, lname, email, pass, created_at);
    }

    @Override
    public void updateUserName(int id, String newUsername) {
        jdbcTemplate.update(UserDaoQueries.updateUserName, newUsername, id);
    }

    @Override
    public void updateUserFirstName(int id, String newFirstName) {
        jdbcTemplate.update(UserDaoQueries.updateUserFirstNameQuery, newFirstName, id);
    }

    @Override
    public void updateUserLastName(int id, String newLastName) {
        jdbcTemplate.update(UserDaoQueries.updateUserLastNameQuery, newLastName, id);
    }

    @Override
    public void updateUserEmail(int id, String newEmail) {
        jdbcTemplate.update(UserDaoQueries.updateUserEmailQuery, newEmail, id);
    }

    @Override
    public boolean isUserExists(String username, String email) {
        if (isUserWithUsername(username) == 1 || isUserWithEmail(email) == 1) {
            return true;
        } else return false;
    }

    @Override
    public List<User> getUsersByLesson(int lessonId) {
        return jdbcTemplate.query(UserDaoQueries.getUsersByLessonQuery, new Object[]{lessonId}, new UserRowMapper());
    }

    @Override
    public User getTrainerByCourse(int courseId) {
        return (User) jdbcTemplate.queryForObject(UserDaoQueries.getTrainerByCourseQuery,
                new Object[]{courseId}, new UserRowMapper());

    }

    @Override
    public List getStudentsOfTrainer(String username) {
        return jdbcTemplate.query(UserDaoQueries.getStudentsOfTrainer, new StudentRowMapper(), username);
    }

    @Override
    public List getTrainersInfo() {
        return jdbcTemplate.query(UserDaoQueries.getTrainersInfoQuery, new TrainersInfoDtoMapper());
    }

    @Override
    public List<String> getTrainerCourses(int id) {
        return jdbcTemplate.queryForList(UserDaoQueries.getTrainerCourses, String.class, id);
    }

    @Override
    public List getStudentsByCourseName(String course) {
        return jdbcTemplate.query(UserDaoQueries.getStudentsByCourseName, new CourseAttendeeMapper(), course);
    }

    @Override
    public void insertTrainerInfo(int id, String info) {
        jdbcTemplate.update(UserDaoQueries.insertTrainerInfoQuery, id, info);
    }

    @Override
    public int getId(String username) {
        return jdbcTemplate.queryForObject(UserDaoQueries.getIdQuery, Integer.class, username);
    }

    private int isUserWithUsername(String username) {
        return jdbcTemplate.queryForObject(UserDaoQueries.isUserWithUsernameExistsQuery, Integer.class, username);
    }

    private int isUserWithEmail(String email) {
        return jdbcTemplate.queryForObject(UserDaoQueries.isUserWithEmailExistsQuery, Integer.class, email);
    }
}