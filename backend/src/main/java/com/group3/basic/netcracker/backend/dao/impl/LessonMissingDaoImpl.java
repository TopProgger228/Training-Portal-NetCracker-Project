package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.LessonMissingDao;
import com.group3.basic.netcracker.backend.entity.Course;
import com.group3.basic.netcracker.backend.entity.Lesson;
import com.group3.basic.netcracker.backend.entity.LessonMissing;
import com.group3.basic.netcracker.backend.entity.User;
import com.group3.basic.netcracker.backend.util.rowmapper.LessonMissingRowMapper;
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

        String SQL = "select lm.id, lm.lesson_id, lm.user_id, lm.reason from \"LessonMissing\" lm join \"Lesson\" l on lm.lesson_id = l.id join \"Course\" c on l.course_id = c.id where c.id = ?";

        return jdbcTemplate.query(SQL, new Object[] {courseId}, new LessonMissingRowMapper());
    }

    @Override
    public List<LessonMissing> getLessonMissingByLesson(int lessonId) {
        String SQL = "select lm.id, lm.lesson_id, lm.user_id, lm.reason from \"LessonMissing\" lm join \"Lesson\" l on lm.lesson_id = l.id where l.id = ?";

        return jdbcTemplate.query(SQL, new Object[] {lessonId}, new LessonMissingRowMapper());
    }

    @Override
    public List<LessonMissing> getLessonMissingByUser(int userId) {
        String SQL = "select lm.id, lm.lesson_id, lm.user_id, lm.reason from \"LessonMissing\" lm where lm.user_id = ?";

        return jdbcTemplate.query(SQL, new Object[] {userId}, new LessonMissingRowMapper());
    }

    @Override
    public List<LessonMissing> getLessonMissingByTrainer(int trainerId) {
        String SQL = "select lm.id, lm.lesson_id, lm.user_id, lm.reason from \"LessonMissing\" lm join \"Lesson\" l on lm.lesson_id = l.id join \"Course\" c on l.course_id = c.id where c.user_id = ?";

        return jdbcTemplate.query(SQL, new Object[] {trainerId}, new LessonMissingRowMapper());
    }

    @Override
    public void add(int userId, int lessonId, String reason) {
        String SQL = "insert into \"LessonMissing\" (user_id, lesson_id, reason) values (?, ?, ?)";
        jdbcTemplate.update(SQL, userId, lessonId, reason);
    }

    @Override
    public void delete(int userId, int lessonId) {
        String SQL = "delete from \"LessonMissing\" where user_id = ? and lesson_id = ?";
        jdbcTemplate.update(SQL, userId, lessonId);
    }

    @Override
    public void updateReason(int userId, int lessonId, String reason) {
        String SQL = "update \"LessonMissing\" set reason = ? where user_id = ? and lesson_id = ?";
        jdbcTemplate.update(SQL, reason, userId, lessonId);
    }

    @Override
    public int getMissingLessonCountByUserAndCourse(int courseId, int userId) {

        String SQL = "select count(lm.id) from \"LessonMissing\" lm left join \"Lesson\" l on lm.lesson_id = L.id left join \"Course\" c on l.course_id = c.id where c.id =? and lm.user_id = ?";

        return jdbcTemplate.queryForObject(SQL, new Object[] {courseId, userId}, Integer.class);
    }
}
