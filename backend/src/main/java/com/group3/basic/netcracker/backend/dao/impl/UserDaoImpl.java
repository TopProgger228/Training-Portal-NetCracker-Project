package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.UserDao;
import com.group3.basic.netcracker.backend.dto.UserForDisplay;
import com.group3.basic.netcracker.backend.entity.User;
import com.group3.basic.netcracker.backend.util.dtomapper.CourseAttendeeMapper;
import com.group3.basic.netcracker.backend.util.dtomapper.TrainersInfoDtoMapper;
import com.group3.basic.netcracker.backend.util.rowmapper.*;
import com.group3.basic.netcracker.backend.util.sql.UserDaoQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        String SQL = "SELECT fname, lname, username, email, id , photo FROM \"User\" WHERE username = '" + username + "'";
        UserForDisplay user = (UserForDisplay) jdbcTemplate.query(SQL, new UserProfileRowMapper()).get(0);

        return user;
    }

    public boolean existsByUsername(String username) {
        try {
            jdbcTemplate.queryForObject(UserDaoQueries.existsByUsernameQuery, new Object[]{username},
                    new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean existsByEmail(String email) {
        String SQL = "SELECT count(*) FROM \"User\" WHERE email = ?";
        Long count = (Long) ((Object) jdbcTemplate.queryForObject(UserDaoQueries.existsByEmailQuery,
                new Object[]{email}, Object.class));
        if (count == 0)
            return false;
        return true;
    }

    public User findByUsername(String username) {
        User user = (User) jdbcTemplate.queryForObject(UserDaoQueries.findByUsernameQuery,
                new Object[]{username}, new UserRowMapper());
        return user;
    }

    @Override
    public List listUsers() {
        List Users = jdbcTemplate.query(UserDaoQueries.usersListQuery, new UserRowMapper());
        return Users;
    }

    @Override
    public List getTrainers() {
        List trainers = jdbcTemplate.query(UserDaoQueries.getTrainersQuery, new TrainerRowMapper());
        return trainers;
    }

    @Override
    public List listUsersForDisplay(String role) {
        String SQL = ("SELECT fname, lname, username, email, id FROM \"User\" WHERE role = '" + role + "';");
        List Users = jdbcTemplate.query(SQL, new UserForDisplayRowMapper());
        return Users;
    }

    @Override
    public List<User> getStudentsOfManager(String username) {

        String SQL = ("SELECT u.*, man.\"Manager\" \n" +
                "FROM \"User\" u\n" +
                "JOIN (SELECT id AS m_id, username AS \"Manager\" \n" +
                "\t\t   FROM \"User\") AS man ON man.m_id = u.manager_id\n" +
                "\t\t WHERE man.\"Manager\" LIKE '" + username + "';");

        List<User> Users = jdbcTemplate.query(SQL, new UserRowMapper());
        return Users;

    }

    @Override
    public List getManagerOfStudent(String username) {
        String SQL = "SELECT m.* FROM \"User\" AS m\n" +
                "WHERE m.id IN (\n" +
                "SELECT w.manager_id FROM\n" +
                "(SELECT u.manager_id AS manager_id, t.trainer AS \"Trainer\" FROM \"User\" u \n" +
                "                JOIN \"Group\" g ON g.user_id = u.id JOIN \"Course\" c ON c.id = g.course_id \n" +
                "                JOIN (SELECT c.id AS course, u.username AS trainer FROM \"User\" u JOIN \"Course\" c ON c.trainer_id = u.id) \n" +
                "AS t ON t.course = course_id \n" +
                "--left join (SELECT id as m_id, username, fname, lname, email as \"Manager\" from \"User\") as man on man.m_id = u.manager_id\n" +
                "                WHERE t.trainer LIKE '" + username + "') AS w);";
        List Users = jdbcTemplate.query(SQL, new UserRowMapper());
        return Users;
    }

    @Override
    public void removeUser(int id) {
        jdbcTemplate.update(UserDaoQueries.removeUserQuery, id);
        System.out.println("User with id: " + id + " successfully removed");
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
        String SQL = "select u.username, u.fname, u.lname, u.email, \n" +
                "man.fname as \"ManagerFname\", man.lname as \"ManagerLname\",man.email as \"ManagerMail\", man.username as \"ManagerUsername\"\n" +
                "from \"User\" u\n" +
                "join \"Group\" g on g.user_id = u.id\n" +
                "join \"Course\" c on c.id = g.course_id\n" +
                "left join (select c.id as course_id, u.username as trainer\n" +
                "from \"User\" u \n" +
                "join \"Course\" c on c.trainer_id = u.id) as t on t.course_id = g.course_id\n" +
                "left join (select id, username, fname, lname, email from \"User\" ) as man on man.id = u.manager_id\n" +
                "where t.trainer like '" + username + "' order by u.username";
        return jdbcTemplate.query(SQL, new StudentRowMapper());
    }

    @Override
    public List getTrainersInfo() {
        return jdbcTemplate.query(UserDaoQueries.getTrainersInfoQuery, new TrainersInfoDtoMapper());
    }

    @Override
    public List<String> getTrainerCourses(int id) {
        String SQL = "select name from \"Course\" where trainer_id = " + id + "";
        return jdbcTemplate.queryForList(SQL, String.class);
    }

    @Override
    public List getStudentsByCourseName(String course) {
        String SQL = "select fname, lname from \"User\" u join \"Group\" g\n" +
                "on u.id = g.user_id join \"Course\" c on g.course_id = c.id where c.name = '" + course + "'";
        return jdbcTemplate.query(SQL, new CourseAttendeeMapper());
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