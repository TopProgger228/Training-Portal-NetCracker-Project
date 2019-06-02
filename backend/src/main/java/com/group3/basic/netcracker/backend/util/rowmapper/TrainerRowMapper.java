package com.group3.basic.netcracker.backend.util.rowmapper;

import com.group3.basic.netcracker.backend.entity.Trainer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerRowMapper implements RowMapper {

    @Override
    public Trainer mapRow(ResultSet row, int rowNum) throws SQLException {
        Trainer trainer = new Trainer();
        trainer.setId(row.getInt("id"));
        trainer.setUsername(row.getString("username"));
        trainer.setFname(row.getString("fname"));
        trainer.setLname(row.getString("lname"));
        return trainer;
    }
}
