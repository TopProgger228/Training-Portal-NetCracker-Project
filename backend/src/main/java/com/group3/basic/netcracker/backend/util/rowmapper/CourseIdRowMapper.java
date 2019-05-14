package com.group3.basic.netcracker.backend.util.rowmapper;

import com.group3.basic.netcracker.backend.entity.Course;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseIdRowMapper implements RowMapper {
    @Override
    public Course mapRow(ResultSet row, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(row.getInt("id"));
        return course;
    }
}
