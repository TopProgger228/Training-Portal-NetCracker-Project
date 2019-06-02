package com.group3.basic.netcracker.backend.util.rowmapper;

import com.group3.basic.netcracker.backend.entity.TimeSlot;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class TimeSlotRowMapper implements RowMapper {

    @Override
    public TimeSlot mapRow(ResultSet row, int rowNum) throws SQLException {
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setId(row.getInt("id"));
        timeSlot.setStartTime(row.getObject("start_time", LocalTime.class));
        timeSlot.setEndTime(row.getObject("end_time", LocalTime.class));
        timeSlot.setWeekDay(row.getString("week_day"));
        timeSlot.setCourseId(row.getInt("course_id"));
        return timeSlot;
    }
}
