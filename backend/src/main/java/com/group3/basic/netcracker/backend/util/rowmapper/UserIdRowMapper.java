package com.group3.basic.netcracker.backend.util.rowmapper;

import com.group3.basic.netcracker.backend.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserIdRowMapper implements RowMapper {

    @Override
    public User mapRow(ResultSet row, int rowNum) throws SQLException {
        User user = new User();
        user.setId(row.getInt("id"));
        return user;
    }
}
