package com.group3.basic.netcracker.backend.timeslot;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeSlotRowMapper implements RowMapper<TimeSlot> {

	@Override
	public TimeSlot mapRow(ResultSet row, int rowNum) throws SQLException {
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(row.getTime("start_time"));
		timeSlot.setEndTime(row.getTime("end_time"));
		timeSlot.setWeekDay(row.getString("week_day"));
		return timeSlot;
	}
}
