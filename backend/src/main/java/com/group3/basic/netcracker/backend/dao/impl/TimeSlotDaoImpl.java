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
        String SQL = "SELECT id, start_time, end_time, week_day, course_id FROM \"TimeSlot\"";
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
    public TimeSlot getTimeSlotByLessonId(int id) {

        String SQL = "SELECT ts.id, ts.start_time, ts.end_time, ts.week_day, ts.course_id FROM \"TimeSlot\" ts join \"Lesson\" l on ts.id = l.time_slot_id where l.id = ?";

        return (TimeSlot)jdbcTemplate.queryForObject(SQL, new Object[] {id}, new TimeSlotRowMapper());
    }

    @Override
    public List getTimeslotsOfCourse(String name){
        String SQL = "SELECT t.id, t.start_time, t.end_time, t.week_day, t.course_id FROM \"TimeSlot\" t JOIN \"Course\" c ON t.course_id = c.id WHERE c.name LIKE '"+ name +"';";
        List timeSlots = jdbcTemplate.query(SQL, new TimeSlotRowMapper());
        return timeSlots;
    }

    @Override
	public void createTimeSlot(LocalTime of, LocalTime of2, String weekDay, int courseId) {
		 String SQL = "INSERT INTO \"TimeSlot\" (start_time, end_time, week_day, course_id) VALUES (?,?,?, ?)";
	        jdbcTemplate.update(SQL, of, of2, weekDay, courseId );
	        System.out.println("TimeSlot created.");
	}

    @Override
    public boolean isTimeslotExists(LocalTime of, LocalTime of2, String weekDay, int courseId) {
        if (isTimeslotWithStartTime(of) == 1 && isTimeslotWithEndTime(of2) == 1 &&
        isTimeslotWithWeekDay(weekDay) == 1 && isTimeslotWithCourseId(courseId) == 1) {
            return true;
        } else return false;
    }

    private int isTimeslotWithStartTime(LocalTime startTime) {
        String SQL = "SELECT COUNT(*) FROM \"TimeSlot\" WHERE start_time = ?";
        return jdbcTemplate.queryForObject(SQL, Integer.class, startTime);
    }

    private int isTimeslotWithEndTime(LocalTime endTime) {
        String SQL = "SELECT COUNT(*) FROM \"TimeSlot\" WHERE end_time = ?";
        return jdbcTemplate.queryForObject(SQL, Integer.class, endTime);
    }

    private int isTimeslotWithWeekDay(String weekDay) {
        String SQL = "SELECT COUNT(*) FROM \"TimeSlot\" WHERE week_day = ?";
        return jdbcTemplate.queryForObject(SQL, Integer.class, weekDay);
    }

    private int isTimeslotWithCourseId(int courseId) {
        String SQL = "SELECT COUNT(*) FROM \"TimeSlot\" WHERE course_id = ?";
        return jdbcTemplate.queryForObject(SQL, Integer.class, courseId);
    }
}