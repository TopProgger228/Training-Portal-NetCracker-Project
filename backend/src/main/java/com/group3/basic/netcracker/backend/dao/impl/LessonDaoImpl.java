package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.LessonDao;
import com.group3.basic.netcracker.backend.entity.Lesson;
import com.group3.basic.netcracker.backend.util.rowmapper.LessonRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class LessonDaoImpl implements LessonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LessonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Lesson> getLessonByCourse(int courseId) {

        String SQL = "select l.id, l.is_cancel, l.lesson_date, l.time_slot_id, l.course_id from \"Lesson\" l join \"Course\" c on l.course_id = c.id where c.id = ?";

        return jdbcTemplate.query(SQL, new Object[] {courseId}, new LessonRowMapper());
    }

    @Override
    public Lesson getLessonById (int lessonId) {

        String SQL = "select l.id, l.is_cancel, l.lesson_date, l.time_slot_id, l.course_id from \"Lesson\" l where l.id = ?";

        return (Lesson) jdbcTemplate.queryForObject(SQL, new Object[] {lessonId}, new LessonRowMapper());

    }

    @Override
    public List<Lesson> getTodayLessonsByTrainer(int trainerId) {

        String SQL = "select l.id, l.is_cancel, l.lesson_date, l.time_slot_id, l.course_id from \"Lesson\" l join \"Course\" c on l.course_id = c.id where c.trainer_id = ? and l.lesson_date = cast(current_timestamp as date)";

        return jdbcTemplate.query(SQL, new Object[] {trainerId}, new LessonRowMapper());
    }

    @Override
    public List<Lesson> getTodayLessonsByTrainerUsername(String username) {

        String SQL = "select l.id, l.is_cancel, l.lesson_date, l.time_slot_id, l.course_id from \"Lesson\" l join \"Course\" c on l.course_id = c.id join \"User\" u on c.trainer_id = u.id where u.username = ? and l.lesson_date = cast(current_timestamp as date)";

        return jdbcTemplate.query(SQL, new Object[] {username}, new LessonRowMapper());
    }
}
