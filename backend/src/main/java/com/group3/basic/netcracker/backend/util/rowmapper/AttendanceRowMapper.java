package com.group3.basic.netcracker.backend.util.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.group3.basic.netcracker.backend.entity.Attendance;

public class AttendanceRowMapper implements RowMapper<Attendance> {

    @Override
    public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setMissingCount(rs.getInt("MissingCount"));
        attendance.setMissingUser(rs.getString("MissingUser"));
        attendance.setCourse(rs.getString("Course"));
        attendance.setReason(rs.getString("Reason"));
        return attendance;
    }

}
