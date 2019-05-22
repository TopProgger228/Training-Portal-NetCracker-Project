package com.group3.basic.netcracker.backend.util.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.group3.basic.netcracker.backend.entity.Schedule;
import org.springframework.jdbc.core.RowMapper;

public class ScheduleRowMapper implements RowMapper {

    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Schedule shedule = new Schedule();
        shedule.setUserId(rs.getInt("user_id"));
        shedule.setTimeSlotId(rs.getInt("time_slot_id"));
        shedule.setChoosen(rs.getBoolean("is_choosen"));
        return shedule;
    }

}
