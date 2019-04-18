package com.group3.basic.netcracker.backend.UserTable.dao.jdbc;

import com.group3.basic.netcracker.backend.UserTable.dao.UserDAO;
import com.group3.basic.netcracker.backend.UserTable.model.User;
import com.group3.basic.netcracker.backend.UserTable.util.UserMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

public class JdbcTemplateUserDaoImpl implements UserDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public void createUser(String username, int role_id, String fname, String lname, String email, String pass, LocalDate created_at, byte[] photo) {
        String SQL = "INSERT INTO \"User\" (username, role_id, fname, lname, email, pass, created_at, photo) VALUES (?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(SQL, username, role_id, fname, lname, email, pass, created_at, photo);
        System.out.println("User successfully created.\nUsername: " + username + ";\nFirst name: " +
                fname + ";\nLast name: " + lname + "\nEmail: " + email + "\nPassword: " + pass + "\nCreated at: " + created_at + "\nPhoto: " + photo);
    }

    @Override
    public User getUserById(int id) {
        String SQL = "SELECT * FROM \"User\" WHERE id = ?";
        User user = (User) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new UserMapper());
        return user;
    }

    public boolean existsByUsername(String username) {
        String SQL = "SELECT * FROM \"User\" WHERE username = ?";
        try {
            jdbcTemplate.queryForObject(SQL, new Object[]{username}, new UserMapper());
        }catch(EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }

    public User findByUsername(String username) {
        String SQL = "SELECT * FROM \"User\" WHERE username = ?";
        User user = (User) jdbcTemplate.queryForObject(SQL, new Object[]{username}, new UserMapper());
        return user;
    }

    @Override
    public List listUsers() {
        String SQL = "SELECT * FROM \"User\"";
        List Users = jdbcTemplate.query(SQL, new UserMapper());
        return Users;
    }

    @Override
    public void removeUser(int id) {
        String SQL = "DELETE FROM \"User\" WHERE id = ?";
        jdbcTemplate.update(SQL, id);
        System.out.println("User with id: " + id + " successfully removed");
    }

    @Override
    public void updateUser(String username, int role_id, String fname, String lname, String email, String pass, LocalDate created_at, byte[] photo) {
        String SQL = "UPDATE \"User\" SET username = ?, role_id = ?, fname = ?, lname = ?, email = ?, pass = ?, created_at = ?, photo = ? WHERE id = ?";
        jdbcTemplate.update(SQL, username, role_id, fname, lname, email, pass, created_at, photo);
        System.out.println("User with id: " + username + " successfully updated.");
    }
}