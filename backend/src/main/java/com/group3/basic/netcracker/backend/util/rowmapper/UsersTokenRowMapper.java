package com.group3.basic.netcracker.backend.util.rowmapper;

import com.group3.basic.netcracker.backend.entity.UsersToken;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersTokenRowMapper implements RowMapper {

    @Override
    public UsersToken mapRow(ResultSet row, int rowNum) throws SQLException {
        UsersToken usersToken = new UsersToken();

        usersToken.setEmail(row.getString("email"));
        usersToken.setToken(row.getString("token"));
        usersToken.setId(row.getInt("id"));
        usersToken.setExpiryDate(row.getObject("expiry_date", Date.class));
        return usersToken;
    }
}
