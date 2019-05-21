package com.group3.basic.netcracker.backend.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.group3.basic.netcracker.backend.dao.ScheduleDao;
import com.group3.basic.netcracker.backend.entity.Schedule;
import com.group3.basic.netcracker.backend.util.rowmapper.ScheduleRowMapper;
import com.group3.basic.netcracker.backend.util.rowmapper.ScheduleWithInfoRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Transactional
@Repository
public class ScheduleDaoImpl implements ScheduleDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

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
    public List listScheduleWithCourseAndTimeSlotAndUser() {
        String SQL = "select c.id,c.name as \"courseName\", count(sc.id) as \"countVoted\",\n" +
                "case when coalesce(cast(t.choose as varchar), 'Not set yet') = 'true'\n" +
                "then 'Schedule has been set' else 'Not set yet' end as \"isScheduled\"\n" +
                "from \"Course\" c\n" +
                "left join \"TimeSlot\" ts on ts.course_id = c.id\n" +
                "left join \"Schedule\" sc on sc.time_slot_id = ts.id\n" +
                "left join \"User\" u on u.id = sc.user_id\n" +
                "left join (select distinct c.name as course, sc.is_choosen as choose\n" +
                "from \"Schedule\" sc\n" +
                "join \"TimeSlot\" ts on ts.id = sc.time_slot_id\n" +
                "join \"Course\" c on c.id = ts.course_id\n" +
                "where is_choosen is true) as t on t.course = c.name\n" +
                "group by c.id, c.name, t.choose\n" +
                "order by c.name";
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
    public void createSchedule(int userId, Integer[] timeSlotId, boolean isChoosen) {
        String SQL = "INSERT INTO \"Schedule\" (user_id, time_slot_id, is_choosen) VALUES (?,?,?)";
        for (int i = 0; i < timeSlotId.length; i++) {
            jdbcTemplate.update(SQL, userId, timeSlotId[i], isChoosen);
            System.out.println("Schedule " + i + " created.");
        }
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
            if (isScheduleWithAllRow(userId, timeSlotId[i], isChoosen) >= 1) {
                continue;
            } else{
                list.add(timeSlotId[i]);
            }
        }

        if (list.size() > 0){
            Integer[] tempArray = new Integer[list.size()];
            tempArray = list.toArray(tempArray);
            return tempArray;
        }
        return null;
    }

    private int isScheduleWithAllRow(int userId, int timeSlotIdOne, boolean isChoosen) {
        String SQL = "SELECT COUNT(*) FROM \"Schedule\" WHERE (user_id = ? AND time_slot_id = ?\n" +
                "AND is_choosen = ?)";//порядок ВАЖЕН!!!
        return jdbcTemplate.queryForObject(SQL, Integer.class, userId, timeSlotIdOne, isChoosen);//порядок ВАЖЕН!!!


    }

}


