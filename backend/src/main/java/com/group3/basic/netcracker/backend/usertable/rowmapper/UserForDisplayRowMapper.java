package com.group3.basic.netcracker.backend.usertable.rowmapper;

import com.group3.basic.netcracker.backend.usertable.entity.UserForDisplay;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserForDisplayRowMapper implements RowMapper {

    @Override
    public UserForDisplay mapRow(ResultSet row, int rowNum) throws SQLException {
        UserForDisplay user = new UserForDisplay();
        user.setUsername(row.getString("username"));
        user.setFname(row.getString("fname"));
        user.setLname(row.getString("lname"));
        return user;
    }
}