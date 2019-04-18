package com.group3.basic.netcracker.backend.UserTable.util;

import com.group3.basic.netcracker.backend.UserTable.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserMapper implements RowMapper {


    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUsername(rs.getString("username"));
        user.setRole_id(rs.getInt("role_id"));
        user.setFname(rs.getString("fname"));
        user.setLname(rs.getString("lname"));
        user.setEmail(rs.getString("email"));
        user.setPass(rs.getString("pass"));
        user.setCreated_at(rs.getObject("created_at", LocalDate.class));
        user.setPhoto(rs.getBytes("photo"));
        //user.setId(rs.getInt("id"));
        return user;
    }
}