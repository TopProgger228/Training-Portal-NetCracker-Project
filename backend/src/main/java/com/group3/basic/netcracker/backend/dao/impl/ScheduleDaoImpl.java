package com.group3.basic.netcracker.backend.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.group3.basic.netcracker.backend.dao.ScheduleDao;
import com.group3.basic.netcracker.backend.entity.Schedule;
import com.group3.basic.netcracker.backend.util.rowmapper.ScheduleOfStudentRowMapper;
import com.group3.basic.netcracker.backend.util.rowmapper.ScheduleRowMapper;
import com.group3.basic.netcracker.backend.util.rowmapper.ScheduleWithInfoRowMapper;
import com.group3.basic.netcracker.backend.util.sql.ScheduleDaoQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
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
        Schedule shedule = (Schedule) jdbcTemplate.queryForObject(ScheduleDaoQueries.getScheduleByIdQuery,
                new Object[]{id}, new ScheduleRowMapper());
        return shedule;
    }


    @Override
    public List listSchedule() {
        return jdbcTemplate.query(ScheduleDaoQueries.getScheduleListQuery, new ScheduleRowMapper());
    }

    @Override
    public List listScheduleWithCourseAndTimeSlotAndUser() {
        return jdbcTemplate.query(ScheduleDaoQueries.listScheduleWithCourseAndTimeSlotAndUserQuery,
                new ScheduleWithInfoRowMapper());
    }

    @Override
    public void removeSchedule(int id) {
        jdbcTemplate.update(ScheduleDaoQueries.removeScheduleQuery, id);
    }

    @Override
    public void updateSchedule(int userId, int timeSlotId, boolean isChoosen, int id) {
        jdbcTemplate.update(ScheduleDaoQueries.updateScheduleQuery, userId, timeSlotId, isChoosen);
    }

    @Override
    public void createSchedule(int userId, Integer[] timeSlotId, boolean isChoosen) {
        for (int i = 0; i < timeSlotId.length; i++) {
            jdbcTemplate.update(ScheduleDaoQueries.createScheduleQuery, userId, timeSlotId[i], isChoosen);
        }
    }

    @Override
    public List getScheduleOfStudent(String username){
        String SQL = "select C2.name, TS.start_time, TS.end_time, TS.week_day, s.is_choosen from \"Schedule\" as s\n" +
                "join \"User\" U on s.user_id = U.id\n" +
                "join \"TimeSlot\" TS on s.time_slot_id = TS.id\n" +
                "join \"Course\" C2 on TS.course_id = C2.id\n" +
                "where U.username like '" + username + "'";
        return jdbcTemplate.query(SQL, new ScheduleOfStudentRowMapper());
    }

    @Override
    public void generateSchedule(int course) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("schedule_setter");

        SqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("course", course);

        Integer result = call.executeFunction(Integer.class, paramMap);
        System.out.println(result);
    }

    @Override
    public Integer[] isScheduleExists(int userId, Integer[] timeSlotId, boolean isChoosen) {
        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < timeSlotId.length; i++) {
            if (isScheduleWithAllRow(userId, timeSlotId[i], isChoosen) < 1) {
                list.add(timeSlotId[i]);
            }
        }

        if (list.size() > 0) {
            Integer[] tempArray = new Integer[list.size()];
            tempArray = list.toArray(tempArray);
            return tempArray;
        }
        return null;
    }

    private int isScheduleWithAllRow(int userId, int timeSlotIdOne, boolean isChoosen) {
        return jdbcTemplate.queryForObject(ScheduleDaoQueries.isScheduleWithAllRowQuery,
                Integer.class, userId, timeSlotIdOne, isChoosen);
    }

}


