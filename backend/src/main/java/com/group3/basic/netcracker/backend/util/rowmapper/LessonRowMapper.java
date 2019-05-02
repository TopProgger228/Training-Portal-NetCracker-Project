package com.group3.basic.netcracker.backend.util.rowmapper;

import com.group3.basic.netcracker.backend.entity.Lesson;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Lesson lesson = new Lesson();
        lesson.setLessonId(rs.getInt("id"));
        lesson.setCourseId(rs.getInt("course_id"));
        lesson.setCancel(rs.getBoolean("is_cancel"));
        lesson.setStartDateTime(rs.getDate("start_date_time"));

        return lesson;
    }
}
