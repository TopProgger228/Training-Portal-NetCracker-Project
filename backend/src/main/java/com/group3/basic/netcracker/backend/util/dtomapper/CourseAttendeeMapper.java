package com.group3.basic.netcracker.backend.util.dtomapper;

import com.group3.basic.netcracker.backend.dto.CourseAttendee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseAttendeeMapper implements RowMapper {
    @Override
    public CourseAttendee mapRow(ResultSet row, int rowNum) throws SQLException {
        CourseAttendee attendee = new CourseAttendee();
        attendee.setFname(row.getString("fname"));
        attendee.setLname(row.getString("lname"));
        return attendee;
    }
}
