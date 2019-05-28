package com.group3.basic.netcracker.backend.util.lessonfunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class LessonGenerate {
    @Autowired
    private DataSource dataSource;

    public void generateSchedule(int course) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        SimpleJdbcCall call = new SimpleJdbcCall(template)
                .withFunctionName("create_lessons");

        SqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("course", course);

        call.executeFunction(Integer.class, paramMap);
        System.out.println("Lessons has been created");
    }
}