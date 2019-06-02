package com.group3.basic.netcracker.backend.util.rowmapper;

import com.group3.basic.netcracker.backend.dto.CourseWithTrainer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CourseWithTrainerRowMapper implements RowMapper {
    public CourseWithTrainer mapRow(ResultSet row, int rowNum) throws SQLException {
        CourseWithTrainer courseWithTrainer = new CourseWithTrainer();
        courseWithTrainer.setName(row.getString("name"));
        courseWithTrainer.setInfo(row.getString("info"));
        courseWithTrainer.setStart_date(row.getObject("start_date", LocalDate.class));
        courseWithTrainer.setEnd_date(row.getObject("end_date", LocalDate.class));
        courseWithTrainer.setSkill_level(row.getString("skill_level"));
        courseWithTrainer.setQty_per_week(row.getInt("qty_per_week"));
        courseWithTrainer.setUsername(row.getString("username"));
        courseWithTrainer.setFname(row.getString("fname"));
        courseWithTrainer.setLname(row.getString("lname"));
        courseWithTrainer.setEmail(row.getString("email"));
        return courseWithTrainer;
    }
}
