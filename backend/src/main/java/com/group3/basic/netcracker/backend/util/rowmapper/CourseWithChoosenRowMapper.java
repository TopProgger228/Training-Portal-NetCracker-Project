package com.group3.basic.netcracker.backend.util.rowmapper;

import com.group3.basic.netcracker.backend.dto.CourseWithChoosen;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CourseWithChoosenRowMapper implements RowMapper {
    public CourseWithChoosen mapRow(ResultSet row, int rowNum) throws SQLException {
        CourseWithChoosen courseWithChoosen = new CourseWithChoosen();
        courseWithChoosen.setId(row.getInt("id"));
        courseWithChoosen.setName(row.getString("name"));
        courseWithChoosen.setInfo(row.getString("info"));
        courseWithChoosen.setStart_date(row.getObject("start_date", LocalDate.class));
        courseWithChoosen.setEnd_date(row.getObject("end_date", LocalDate.class));
        courseWithChoosen.setSkill_level(row.getString("skill_level"));
        courseWithChoosen.setTrainer_id(row.getInt("trainer_id"));
        courseWithChoosen.setQty_per_week(row.getInt("qty_per_week"));
        courseWithChoosen.setChoosen(row.getString("is_choosen"));
        return courseWithChoosen;
    }
}
