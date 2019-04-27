package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.UserDao;
import com.group3.basic.netcracker.backend.entity.User;
import com.group3.basic.netcracker.backend.util.rowmapper.TrainerRowMapper;
import com.group3.basic.netcracker.backend.util.rowmapper.UserForDisplayRowMapper;
import com.group3.basic.netcracker.backend.util.rowmapper.UserRowMapper;
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
    public UserDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void createUser(String username, String  role, String fname, String lname, String email, String pass, LocalDate created_at, byte[] photo) {
        String SQL = "INSERT INTO \"User\" (username, role, fname, lname, email, pass, created_at, photo) VALUES (?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(SQL, username, role, fname, lname, email, pass, created_at, photo);
        System.out.println("User successfully created.\nUsername: " + username + ";\nFirst name: " +
                fname + ";\nLast name: " + lname + "\nEmail: " + email + "\nPassword: " + pass + "\nCreated at: " + created_at + "\nPhoto: " + photo);
    }

    @Override
    public User getUserById(int id) {
        String SQL = "SELECT * FROM \"User\" WHERE id = ?";
        User user = (User) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new UserRowMapper());
        return user;
    }

    public boolean existsByUsername(String username) {
        String SQL = "SELECT * FROM \"User\" WHERE username = ?";
        try {
            jdbcTemplate.queryForObject(SQL, new Object[]{username}, new UserRowMapper());
        }catch(EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }

    public User findByUsername(String username) {
        String SQL = "SELECT * FROM \"User\" WHERE username = ?";
        User user = (User) jdbcTemplate.queryForObject(SQL, new Object[]{username}, new UserRowMapper());
        return user;
    }

    @Override
    public List listUsers() {
        String SQL = "SELECT * FROM \"User\"";
        List Users = jdbcTemplate.query(SQL, new UserRowMapper());
        return Users;
    }

    @Override
    public List getTrainers() {
        String SQL = "SELECT id, fname, lname, username, email FROM \"User\" where role= 'Trainer' ";
        List trainers = jdbcTemplate.query(SQL, new TrainerRowMapper());
        return trainers;
    }

    @Override
    public List listUsersForDisplay(String role) {
        String SQL = ("SELECT fname, lname, username, email, id FROM \"User\" WHERE role = '" + role + "';");
        List Users = jdbcTemplate.query(SQL, new UserForDisplayRowMapper());
        return Users;
    }

    @Override
    public void removeUser(int id) {
        String SQL = "DELETE FROM \"User\" WHERE id = ?";
        jdbcTemplate.update(SQL, id);
        System.out.println("User with id: " + id + " successfully removed");
    }

    @Override
    public void updateUser(int id, String username, String role, String fname, String lname, String email, String pass, LocalDate created_at, byte[] photo) {
        String SQL = "UPDATE \"User\" SET username = ?, role = ?, fname = ?, lname = ?, email = ?, pass = ?, created_at = ?, photo = ? WHERE id = ?";
        jdbcTemplate.update(SQL, username, role, fname, lname, email, pass, created_at, photo, id);
        System.out.println("User with id: " + username + " successfully updated.");
    }

    @Override
    public void addMember(String username, String role, String fname, String lname,String email, String pass, LocalDate created_at) {
        String SQL = "INSERT INTO \"User\" (username, role, fname, lname, email, pass, created_at) VALUES" +
                "(?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(SQL, username, role, fname, lname, email, pass, created_at);
    }

    @Override
    public void updateUserName(int id, String newUsername) {
        String SQL = "UPDATE \"User\" SET username = ? WHERE id = ?";
        jdbcTemplate.update(SQL, newUsername, id);
    }

    @Override
    public void updateUserFirstName(int id, String newFirstName) {
        String SQL = "UPDATE \"User\" SET fname = ? WHERE id = ?";
        jdbcTemplate.update(SQL, newFirstName, id);
    }

    @Override
    public void updateUserLastName(int id, String newLastName) {
        String SQL = "UPDATE \"User\" SET lname = ? WHERE id = ?";
        jdbcTemplate.update(SQL, newLastName, id);
    }

    @Override
    public void updateUserEmail(int id, String newEmail) {
        String SQL = "UPDATE \"User\" SET email = ? WHERE id = ?";
        jdbcTemplate.update(SQL, newEmail, id);
    }

    @Override
    public boolean isUserExists(String username, String email) {
        if (isUserWithUsername(username) == 1 || isUserWithEmail(email) == 1){
            return true;
        }else return false;
    }

    private int isUserWithUsername(String username){
        String SQL = "SELECT COUNT(*) FROM \"User\" WHERE username = ?";
        return jdbcTemplate.queryForObject(SQL, Integer.class, username);
    }

    private int isUserWithEmail(String email){
        String SQL = "SELECT COUNT(*) FROM \"User\" WHERE email = ?";
        return jdbcTemplate.queryForObject(SQL, Integer.class, email);
    }
}