package com.group3.basic.netcracker.backend.util.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.group3.basic.netcracker.backend.entity.StudySchedule;
import org.springframework.jdbc.core.RowMapper;

public class StudyScheduleRowMapper implements RowMapper {

	@Override
	public StudySchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
		StudySchedule shedule = new StudySchedule();
		shedule.setCourse(rs.getInt("course_id"));
		shedule.setUserId(rs.getInt("user_id"));
		shedule.setTimeSlotId(rs.getInt("time_slot_id"));
		shedule.setChoosen(rs.getBoolean("is_choosen"));
		return shedule;
	}

}
