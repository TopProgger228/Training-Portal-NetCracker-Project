package com.group3.basic.netcracker.backend.util.rowmapper;

import com.group3.basic.netcracker.backend.dto.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper {
    @Override
    public Student mapRow(ResultSet row, int rowNum) throws SQLException {
        Student student = new Student();
        student.setUsername(row.getString("username"));
        student.setFname(row.getString("fname"));
        student.setLname(row.getString("lname"));
        student.setEmail(row.getString("email"));
        student.setManagerFname(row.getString("ManagerFname"));
        student.setManagerLname(row.getString("ManagerLname"));
        student.setManagerEmail(row.getString("ManagerMail"));
        return student;
    }
}
