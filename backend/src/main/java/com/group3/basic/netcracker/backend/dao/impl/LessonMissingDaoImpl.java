package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.LessonMissingDao;
import com.group3.basic.netcracker.backend.entity.LessonMissing;
import com.group3.basic.netcracker.backend.util.rowmapper.LessonMissingRowMapper;
import com.group3.basic.netcracker.backend.util.sql.LessonMissingDaoQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LessonMissingDaoImpl implements LessonMissingDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LessonMissingDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<LessonMissing> getLessonMissingByCourse(int courseId) {
        return jdbcTemplate.query(LessonMissingDaoQueries.getLessonMissingByCourse,
                new Object[]{courseId}, new LessonMissingRowMapper());
    }

    @Override
    public List<LessonMissing> getLessonMissingByLesson(int lessonId) {
        return jdbcTemplate.query(LessonMissingDaoQueries.getLessonMissingByLesson,
                new Object[]{lessonId}, new LessonMissingRowMapper());
    }

    @Override
    public List<LessonMissing> getLessonMissingByUser(int userId) {
        return jdbcTemplate.query(LessonMissingDaoQueries.getLessonMissingByUser,
                new Object[]{userId}, new LessonMissingRowMapper());
    }

    @Override
    public List<LessonMissing> getLessonMissingByTrainer(int trainerId) {
        return jdbcTemplate.query(LessonMissingDaoQueries.getLessonMissingByTrainer,
                new Object[]{trainerId}, new LessonMissingRowMapper());
    }

    @Override
    public void add(int userId, int lessonId, String reason) {
        jdbcTemplate.update(LessonMissingDaoQueries.addLessonMissing, userId, lessonId, reason);
    }

    @Override
    public void delete(int userId, int lessonId) {
        jdbcTemplate.update(LessonMissingDaoQueries.deleteLessonMissing, userId, lessonId);
    }

    @Override
    public void updateReason(int userId, int lessonId, String reason) {
        jdbcTemplate.update(LessonMissingDaoQueries.updateReason, reason, userId, lessonId);
    }

    @Override
    public int getMissingLessonCountByUserAndCourse(int courseId, int userId) {
        return jdbcTemplate.queryForObject(LessonMissingDaoQueries.getMissingLessonCountByUserAndCourse,
                new Object[]{courseId, userId}, Integer.class);
    }
}
