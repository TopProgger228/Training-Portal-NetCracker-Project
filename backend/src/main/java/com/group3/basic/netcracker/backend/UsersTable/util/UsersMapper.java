package com.group3.basic.netcracker.backend.UsersTable.util;

import com.group3.basic.netcracker.backend.UsersTable.model.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

public class UsersMapper implements RowMapper {
    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        Users users = new Users();
        users.setUsername(rs.getString("username"));
        users.setRole_id(rs.getInt("role_id"));
        users.setFname(rs.getString("fname"));
        users.setLname(rs.getString("lname"));
        users.setEmail(rs.getString("email"));
        users.setPass(rs.getString("pass"));
        users.setCreated_at(rs.getObject("created_at", LocalDate.class));
        users.setPhoto(rs.getBytes("photo"));
        users.setId(rs.getInt("id"));
        return users;
    }
}