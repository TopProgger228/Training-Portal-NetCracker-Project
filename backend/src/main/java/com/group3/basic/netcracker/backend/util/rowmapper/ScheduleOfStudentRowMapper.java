package com.group3.basic.netcracker.backend.util.rowmapper;

import com.group3.basic.netcracker.backend.dto.ScheduleOfStudent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleOfStudentRowMapper implements RowMapper {
    @Override
    public ScheduleOfStudent mapRow(ResultSet rs, int rowNum) throws SQLException {
        ScheduleOfStudent scheduleOfStudent = new ScheduleOfStudent();
        scheduleOfStudent.setCourseName(rs.getString("name"));
        scheduleOfStudent.setStartTime(rs.getString("start_time"));
        scheduleOfStudent.setEndTime(rs.getString("end_time"));
        scheduleOfStudent.setWeekDay(rs.getString("week_day"));
        scheduleOfStudent.setIsScheduled(rs.getString("is_choosen"));
        return scheduleOfStudent;
    }
}
