package com.group3.basic.netcracker.backend.util.rowmapper;

import com.group3.basic.netcracker.backend.dto.UserForDisplay;
import com.group3.basic.netcracker.backend.util.file.ImageConverter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserForDisplayRowMapper implements RowMapper {

    @Override
    public UserForDisplay mapRow(ResultSet row, int rowNum) throws SQLException {
        UserForDisplay user = new UserForDisplay();
        user.setFname(row.getString("fname"));
        user.setLname(row.getString("lname"));
        user.setUsername(row.getString("username"));
        user.setEmail(row.getString("email"));
        user.setId(row.getInt("id"));
        return user;
    }
}