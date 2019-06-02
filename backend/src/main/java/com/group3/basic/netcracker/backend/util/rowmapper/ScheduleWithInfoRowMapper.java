package com.group3.basic.netcracker.backend.util.rowmapper;

import com.group3.basic.netcracker.backend.dto.ScheduleWithInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleWithInfoRowMapper implements RowMapper {

    @Override
    public ScheduleWithInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        ScheduleWithInfo scheduleWithInfo = new ScheduleWithInfo();
        scheduleWithInfo.setCourseId(rs.getInt("id"));
        scheduleWithInfo.setCourseName(rs.getString("courseName"));
        scheduleWithInfo.setCountVoted(rs.getInt("countVoted"));
        scheduleWithInfo.setIsScheduled(rs.getString("isScheduled"));
        return scheduleWithInfo;
    }
}
