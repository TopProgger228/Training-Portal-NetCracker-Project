package com.group3.basic.netcracker.backend.dao.impl;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import com.group3.basic.netcracker.backend.entity.TimeSlot;
import com.group3.basic.netcracker.backend.util.rowmapper.TimeSlotRowMapper;
import com.group3.basic.netcracker.backend.util.sql.TimeSlotDaoQueries;
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
    public TimeSlotDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TimeSlot getTimeSlotById(int id) {
        return (TimeSlot) jdbcTemplate.queryForObject(TimeSlotDaoQueries.getTimeSlotByIdQuery,
                new Object[]{id}, new TimeSlotRowMapper());
    }

    public TimeSlot findByStartTime(Time startTime) {
        return (TimeSlot) jdbcTemplate.queryForObject(TimeSlotDaoQueries.findByStartTimeQuery,
                new Object[]{startTime}, new TimeSlotRowMapper());
    }

    @Override
    public List listTimeSlots() {
        return jdbcTemplate.query(TimeSlotDaoQueries.getTimeSlotsListQuery, new TimeSlotRowMapper());
    }

    @Override
    public void removeTimeSlot(int id) {
        jdbcTemplate.update(TimeSlotDaoQueries.removeTimeSlotQuery, id);
    }

    @Override
    public void updateTimeSlot(LocalTime localTime, LocalTime localTime2, String weekDay, int courseId, int id) {
        jdbcTemplate.update(TimeSlotDaoQueries.updateTimeSlotQuery, localTime, localTime2, weekDay, courseId, id);
    }

    @Override
    public TimeSlot getTimeSlotByLessonId(int id) {
        return (TimeSlot) jdbcTemplate.queryForObject(TimeSlotDaoQueries.getTimeSlotByLessonIdQuery,
                new Object[]{id}, new TimeSlotRowMapper());
    }

    @Override
    public List getTimeslotsOfCourse(String name) {
        String SQL = "SELECT t.id, t.start_time, t.end_time, t.week_day, t.course_id FROM \"TimeSlot\" t " +
                "JOIN \"Course\" c ON t.course_id = c.id WHERE c.name LIKE '" + name + "';";
        return jdbcTemplate.query(SQL, new TimeSlotRowMapper());
    }

    @Override
    public void createTimeSlot(LocalTime of, LocalTime of2, String weekDay, int courseId) {
        jdbcTemplate.update(TimeSlotDaoQueries.createTimeSlotQuery, of, of2, weekDay, courseId);
    }

    @Override
    public boolean isTimeslotExists(LocalTime of, LocalTime of2, String weekDay, int courseId) {
        if (isTimeSlotWithAllRow(of, of2, weekDay, courseId) >= 1) {
            return true;
        } else return false;
    }

    private int isTimeSlotWithAllRow(LocalTime startTime, LocalTime endTime, String weekDay, int courseId) {
        return jdbcTemplate.queryForObject(TimeSlotDaoQueries.isTimeSlotWitAllRow,
                Integer.class, startTime, endTime, weekDay, courseId);
    }
}