package com.group3.basic.netcracker.backend.usertable.rowmapper;

import com.group3.basic.netcracker.backend.usertable.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserRowMapper implements RowMapper {

    @Override
    public User mapRow(ResultSet row, int rowNum) throws SQLException {
        User user = new User();
        user.setId(row.getInt("id"));
        user.setUsername(row.getString("username"));
        user.setRole(row.getString("role"));
        user.setFname(row.getString("fname"));
        user.setLname(row.getString("lname"));
        user.setEmail(row.getString("email"));
        user.setPass(row.getString("pass"));
        user.setCreated_at(row.getObject("created_at", LocalDate.class));
        user.setPhoto(row.getBytes("photo"));
        user.setManager_id(row.getInt("manager_id"));
        return user;
    }
}