package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.LessonDao;
import com.group3.basic.netcracker.backend.entity.Lesson;
import com.group3.basic.netcracker.backend.util.rowmapper.LessonRowMapper;
import com.group3.basic.netcracker.backend.util.sql.LessonDaoQueries;
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
        return jdbcTemplate.query(LessonDaoQueries.getLessonByCourse, new Object[]{courseId}, new LessonRowMapper());
    }

    @Override
    public Lesson getLessonById(int lessonId) {
        return (Lesson) jdbcTemplate.queryForObject(LessonDaoQueries.getLessonById,
                new Object[]{lessonId}, new LessonRowMapper());
    }

    @Override
    public List<Lesson> getTodayLessonsByTrainerUsername(String username) {
        List<Lesson> lessonList = jdbcTemplate.query(LessonDaoQueries.getTodayLessonsByTrainerUsername,
                new Object[]{username}, new LessonRowMapper());
        return lessonList;
    }

    @Override
    public int getLessonCountTillTodayByStudent(int userId) {
        return jdbcTemplate.queryForObject(LessonDaoQueries.getLessonCountTillTodayByStudent,
                new Object[]{userId}, Integer.class);
    }

    @Override
    public int getLessonCountInCourseTillToday(int courseId) {
        return jdbcTemplate.queryForObject(LessonDaoQueries.getLessonCountInCourseTillToday,
                new Object[]{courseId}, Integer.class);
    }

    @Override
    public int getLessonCountInCourse(int courseId) {
        return jdbcTemplate.queryForObject(LessonDaoQueries.getLessonCountInCourse,
                new Object[]{courseId}, Integer.class);
    }

    @Override
    public void updateActiveStatus(int lessonId, boolean status) {
        jdbcTemplate.update(LessonDaoQueries.updateActiveStatus, status, lessonId);
    }
}
