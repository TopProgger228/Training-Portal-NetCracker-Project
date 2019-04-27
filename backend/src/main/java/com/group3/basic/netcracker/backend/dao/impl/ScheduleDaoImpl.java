package com.group3.basic.netcracker.backend.dao.impl;

import java.util.List;
import java.util.TreeMap;

import com.group3.basic.netcracker.backend.dao.ScheduleDao;
import com.group3.basic.netcracker.backend.entity.Schedule;
import com.group3.basic.netcracker.backend.util.rowmapper.ScheduleRowMapper;
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
        String SQL = "SELECT * FROM \"Schedule\"";
        List shedule = jdbcTemplate.query(SQL, new ScheduleRowMapper());
        return shedule;
    }

    @Override
    public void removeSchedule(int id) {
        String SQL = "DELETE FROM \"Schedule\" WHERE id = ?";
        jdbcTemplate.update(SQL, id);
        System.out.println("Schedule removed");
    }

    @Override
    public void updateSchedule(int courseId, int userId, int timeSlotId, boolean isChoosen, int id) {
        String SQL = "UPDATE \"Schedule\" SET course_id = ?, time_slot_id = ?, is_choosen = ? WHERE id = ?";
        jdbcTemplate.update(SQL, courseId, userId, timeSlotId, isChoosen);
        System.out.println("Schedule updated.");
    }

    @Override
    public void createSchedule(int courseId, int userId, int timeSlotId, boolean isChoosen) {
        String SQL = "INSERT INTO \"Schedule\" (course_id, user_id, time_slot_id, is_choosen) VALUES (?,?,?,?)";
        jdbcTemplate.update(SQL, courseId, userId, timeSlotId, isChoosen);
        System.out.println("Schedule created.");
    }

    @Override
    public TreeMap<Integer, Integer> getChooseCount(int course) {

        TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
        int timeslot = this.jdbcTemplate.queryForObject("select time_slot_id as TimeSlot from \"Schedule\" "
                + "where course_id = ? group by time_slot_id;", Integer.class, course);
        int count = this.jdbcTemplate.queryForObject(
                "select count(id) as Voted from \"Schedule\"" + "where course_id = ? ;", Integer.class, course);
        tm.put(timeslot, count);
        return tm;
    }
}


