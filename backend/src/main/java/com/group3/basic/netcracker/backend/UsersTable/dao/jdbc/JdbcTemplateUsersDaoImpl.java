package com.group3.basic.netcracker.backend.UsersTable.dao.jdbc;

import com.group3.basic.netcracker.backend.UsersTable.dao.UsersDAO;
import com.group3.basic.netcracker.backend.UsersTable.model.Users;
import com.group3.basic.netcracker.backend.UsersTable.util.UsersMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

public class JdbcTemplateUsersDaoImpl implements UsersDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public void createUser(String username, int role_id, String fname, String lname, String email, String pass, LocalDate created_at, byte[] photo) {
        String SQL = "INSERT INTO \"Users\" (username, role_id, fname, lname, email, pass, created_at, photo) VALUES (?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(SQL, username, role_id, fname, lname, email, pass, created_at, photo);
        System.out.println("Users successfully created.\nUsername: " + username + ";\nFirst name: " +
                fname + ";\nLast name: " + lname + "\nEmail: " + email + "\nPassword: " + pass + "\nCreated at: " + created_at + "\nPhoto: " + photo);
    }

    @Override
    public Users getUserById(int id) {
        String SQL = "SELECT * FROM \"Users\" WHERE id = ?";
        Users users = (Users) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new UsersMapper());
        return users;
    }

    public boolean existsByUsername(String username) {
        String SQL = "SELECT * FROM \"Users\" WHERE username = ?";
        try {
            jdbcTemplate.queryForObject(SQL, new Object[]{username}, new UsersMapper());
        }catch(EmptyResultDataAccessException e){
            return false;
        }
        return true;
    }

    public Users findByUsername(String username) {
        String SQL = "SELECT * FROM \"Users\" WHERE username = ?";
        Users users = (Users) jdbcTemplate.queryForObject(SQL, new Object[]{username}, new UsersMapper());
        return users;
    }

    @Override
    public List listUsers() {
        String SQL = "SELECT * FROM \"Users\"";
        List Users = jdbcTemplate.query(SQL, new UsersMapper());
        return Users;
    }

    @Override
    public void removeUser(int id) {
        String SQL = "DELETE FROM \"Users\" WHERE id = ?";
        jdbcTemplate.update(SQL, id);
        System.out.println("Users with id: " + id + " successfully removed");
    }

    @Override
    public void updateUser(String username, int role_id, String fname, String lname, String email, String pass, LocalDate created_at, byte[] photo) {
        String SQL = "UPDATE \"Users\" SET username = ?, role_id = ?, fname = ?, lname = ?, email = ?, pass = ?, created_at = ?, photo = ? WHERE id = ?";
        jdbcTemplate.update(SQL, username, role_id, fname, lname, email, pass, created_at, photo);
        System.out.println("Users with id: " + username + " successfully updated.");
    }
}