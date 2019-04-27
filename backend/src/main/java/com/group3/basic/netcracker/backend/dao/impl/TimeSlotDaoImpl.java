package com.group3.basic.netcracker.backend.dao.impl;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import com.group3.basic.netcracker.backend.entity.TimeSlot;
import com.group3.basic.netcracker.backend.util.rowmapper.TimeSlotRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.group3.basic.netcracker.backend.dao.TimeSlotDao;

@Transactional
@Repository
public class TimeSlotDaoImpl implements TimeSlotDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TimeSlotDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TimeSlot getTimeSlotById(int id) {
        String SQL = "SELECT * FROM \"TimeSlot\" WHERE id = ?";
        TimeSlot timeSlot = (TimeSlot) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new TimeSlotRowMapper());
        return timeSlot;
    }

    public TimeSlot findByStartTime(Time startTime) {
        String SQL = "SELECT * FROM \"TimeSlot\" WHERE start_time = ?";
        TimeSlot timeSlot = (TimeSlot) jdbcTemplate.queryForObject(SQL, new Object[]{startTime}, new TimeSlotRowMapper());
        return timeSlot;
    }

    @Override
    public List listTimeSlots() {
        String SQL = "SELECT * FROM \"TimeSlot\"";
        List timeSlots = jdbcTemplate.query(SQL, new TimeSlotRowMapper());
        return timeSlots;
    }

    @Override
    public void removeTimeSlot(int id) {
        String SQL = "DELETE FROM \"TimeSlot\" WHERE id = ?";
        jdbcTemplate.update(SQL, id);
        System.out.println("TimeSlot removed");
    }

    @Override
    public void updateTimeSlot(LocalTime localTime, LocalTime localTime2, String weekDay, int courseId, int id) {
        String SQL = "UPDATE \"TimeSlot\" SET start_time = ?, end_time = ?, week_day = ?, course_id = ? WHERE id = ?";
        jdbcTemplate.update(SQL, localTime, localTime2, weekDay, courseId, id );
        System.out.println("TimeSlot updated.");
    }
    @Override
	public void createTimeSlot(LocalTime of, LocalTime of2, String weekDay, int courseId) {
		 String SQL = "INSERT INTO \"TimeSlot\" (start_time, end_time, week_day, course_id) VALUES (?,?,?, ?)";
	        jdbcTemplate.update(SQL, of, of2, weekDay, courseId );
	        System.out.println("TimeSlot created.");
	}
}