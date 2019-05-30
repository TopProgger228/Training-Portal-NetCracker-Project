package com.group3.basic.netcracker.backend.util.rowmapper;

import com.group3.basic.netcracker.backend.entity.LessonMissing;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LessonMissingRowMapper implements RowMapper {


    @Override
    public LessonMissing mapRow(ResultSet rs, int rowNum) throws SQLException {
        LessonMissing lessonMissing = new LessonMissing();
        lessonMissing.setId(rs.getInt("id"));
        lessonMissing.setLessonId(rs.getInt("lesson_id"));
        lessonMissing.setUserId(rs.getInt("user_id"));
        lessonMissing.setReason(rs.getString("reason"));

        return lessonMissing;
    }
}
