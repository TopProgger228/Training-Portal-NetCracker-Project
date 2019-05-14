package com.group3.basic.netcracker.backend.util.rowmapper;

import com.group3.basic.netcracker.backend.dto.ScheduleWithInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleWithInfoRowMapper implements RowMapper {

    @Override
    public ScheduleWithInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        ScheduleWithInfo scheduleWithInfo = new ScheduleWithInfo();
        scheduleWithInfo.setCourseName(rs.getString("name"));
        scheduleWithInfo.setStudentFname(rs.getString("fname"));
        scheduleWithInfo.setStudentLname(rs.getString("lname"));
        scheduleWithInfo.setTimeslotStart_time(rs.getString("start_time"));
        scheduleWithInfo.setTimeslotEnd_time(rs.getString("end_time"));
        scheduleWithInfo.setTimeslotWeek_day(rs.getString("week_day"));
        return scheduleWithInfo;
    }
}
