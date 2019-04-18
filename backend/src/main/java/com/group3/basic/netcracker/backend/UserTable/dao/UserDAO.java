package com.group3.basic.netcracker.backend.UserTable.dao;

import com.group3.basic.netcracker.backend.UserTable.model.User;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

public interface UserDAO {
    public void setDataSource(DataSource dataSource);

    public void createUser(String username, int role_id, String fname, String lname, String email, String pass, LocalDate created_at, byte[] photo);

    public User getUserById(int id);

    public List listUsers();

    public void removeUser(int id);

    public void updateUser(String username, int role_id, String fname, String lname, String email, String pass, LocalDate created_at, byte[] photo);
}
