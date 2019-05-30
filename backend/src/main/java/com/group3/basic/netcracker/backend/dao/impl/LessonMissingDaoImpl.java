package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.LessonMissingDao;
import com.group3.basic.netcracker.backend.entity.LessonMissing;
import com.group3.basic.netcracker.backend.util.rowmapper.LessonMissingRowMapper;
import com.group3.basic.netcracker.backend.util.sql.LessonMissingDaoQueries;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Slf4j
@Repository
public class LessonMissingDaoImpl implements LessonMissingDao {

    private final JdbcTemplate jdbcTemplate;
    private final LessonMissingRowMapper lessonMissingRowMapper;

    @Autowired
    public LessonMissingDaoImpl(JdbcTemplate jdbcTemplate, LessonMissingRowMapper lessonMissingRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.lessonMissingRowMapper = lessonMissingRowMapper;
    }

    @Override
    public List<LessonMissing> getLessonMissingByCourse(int courseId) {
        return jdbcTemplate.query(LessonMissingDaoQueries.getLessonMissingByCourse,
                new Object[]{courseId}, lessonMissingRowMapper);
    }

    @Override
    public List<LessonMissing> getLessonMissingByLesson(int lessonId) {
        return jdbcTemplate.query(LessonMissingDaoQueries.getLessonMissingByLesson,
                new Object[]{lessonId}, lessonMissingRowMapper);
    }

    @Override
    public List<LessonMissing> getLessonMissingByUser(int userId) {
        return jdbcTemplate.query(LessonMissingDaoQueries.getLessonMissingByUser,
                new Object[]{userId}, lessonMissingRowMapper);
    }

    @Override
    public List<LessonMissing> getLessonMissingByTrainer(int trainerId) {
        return jdbcTemplate.query(LessonMissingDaoQueries.getLessonMissingByTrainer,
                new Object[]{trainerId}, lessonMissingRowMapper);
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

    @Override
    public LessonMissing getLessonMissingByLessonIdAndUserId(int lessonId, int userId) {

        String SQL = "select lm.id, lm.user_id, lm.lesson_id, lm.reason from \"LessonMissing\" lm " +
                "where lm.lesson_id = ? and lm.user_id = ?";

        List<LessonMissing> lessonMissingList = jdbcTemplate.query(SQL, new Object[] {lessonId, userId}, lessonMissingRowMapper);
        if (lessonMissingList.size() > 1) {
            log.debug("Duplicate line into lessonMissing table. Line with lesson id - {} and user id - {}", lessonId, userId);
            throw new RuntimeException();
        } else if (lessonMissingList.size() == 1) {
            return lessonMissingList.get(0);
        } else {
            return null;
        }
    }
}
