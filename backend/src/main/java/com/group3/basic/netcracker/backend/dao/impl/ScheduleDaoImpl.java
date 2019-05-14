package com.group3.basic.netcracker.backend.dao.impl;

import java.util.List;
import java.util.TreeMap;

import com.group3.basic.netcracker.backend.dao.ScheduleDao;
import com.group3.basic.netcracker.backend.entity.Schedule;
import com.group3.basic.netcracker.backend.util.rowmapper.ScheduleRowMapper;
import com.group3.basic.netcracker.backend.util.rowmapper.ScheduleWithInfoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ScheduleDaoImpl implements ScheduleDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScheduleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Schedule getScheduleById(int id) {
        String SQL = "SELECT * FROM \"Schedule\" WHERE id = ?";
        Schedule shedule = (Schedule) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new ScheduleRowMapper());
        return shedule;
    }


    @Override
    public List listSchedule() {
        String SQL = "SELECT id, user_id, time_slot_id, is_choosen FROM \"Schedule\"";
        List shedule = jdbcTemplate.query(SQL, new ScheduleRowMapper());
        return shedule;
    }

    @Override
    public List listScheduleWithCourseAndTimeSlotAndUser(){
        String SQL = "select  C2.name, u.fname, u.lname, TS.start_time, TS.end_time, TS.week_day from \"Schedule\" s\n" +
                "join \"User\" U on s.user_id = U.id\n" +
                "join \"TimeSlot\" TS on s.time_slot_id = TS.id\n" +
                "join \"Course\" C2 on TS.course_id = C2.id\n" +
                "order by C2.name, TS.week_day, TS.start_time";
        List schedule = jdbcTemplate.query(SQL, new ScheduleWithInfoRowMapper());
        return schedule;
    }

    @Override
    public void removeSchedule(int id) {
        String SQL = "DELETE FROM \"Schedule\" WHERE id = ?";
        jdbcTemplate.update(SQL, id);
        System.out.println("Schedule removed");
    }

    @Override
    public void updateSchedule(int userId, int timeSlotId, boolean isChoosen, int id) {
        String SQL = "UPDATE \"Schedule\" SET user_id = ?, time_slot_id = ?, is_choosen = ? WHERE id = ?";
        jdbcTemplate.update(SQL, userId, timeSlotId, isChoosen);
        System.out.println("Schedule updated.");
    }

    @Override
    public void createSchedule(int userId, int timeSlotId, boolean isChoosen) {
        String SQL = "INSERT INTO \"Schedule\" (user_id, time_slot_id, is_choosen) VALUES (?,?,?)";
        jdbcTemplate.update(SQL,  userId, timeSlotId, isChoosen);
        System.out.println("Schedule created.");
    }

}


