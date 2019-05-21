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

//        String SQL = "select l.id, l.is_cancel, l.lesson_date, l.time_slot_id, l.course_id from \"Lesson\" l join \"Course\" c on l.course_id = c.id where c.id = ? order by l.lesson_date";

        return jdbcTemplate.query(LessonDaoQueries.getLessonByCourse, new Object[] {courseId}, new LessonRowMapper());
    }

    @Override
    public Lesson getLessonById (int lessonId) {

//        String SQL = "select l.id, l.is_cancel, l.lesson_date, l.time_slot_id, l.course_id from \"Lesson\" l where l.id = ?";

        return (Lesson) jdbcTemplate.queryForObject(LessonDaoQueries.getLessonById, new Object[] {lessonId}, new LessonRowMapper());

    }

    @Override
    public List<Lesson> getTodayLessonsByTrainer(int trainerId) {

//        String SQL = "select l.id, l.is_cancel, l.lesson_date, l.time_slot_id, l.course_id from \"Lesson\" l join \"Course\" c on l.course_id = c.id where c.trainer_id = ? and l.lesson_date = cast(current_timestamp as date)";

        return jdbcTemplate.query(LessonDaoQueries.getTodayLessonsByTrainer, new Object[] {trainerId}, new LessonRowMapper());
    }

    @Override
    public List<Lesson> getTodayLessonsByTrainerUsername(String username) {

//        String SQL = "select l.id, l.is_cancel, l.lesson_date, l.time_slot_id, l.course_id from \"Lesson\" l join \"Course\" c on l.course_id = c.id join \"User\" u on c.trainer_id = u.id where u.username = ? and l.lesson_date = cast(current_timestamp as date)";

        return jdbcTemplate.query(LessonDaoQueries.getTodayLessonsByTrainerUsername, new Object[] {username}, new LessonRowMapper());
    }

    @Override
    public int getLessonCountTillTodayByStudent(int userId) {

//        String SQL = "select count(l.id) from \"Lesson\" l join \"Course\" c on l.course_id = c.id join \"Group\" g on c.id = g.course_id join \"User\" u on g.user_id = u.id join \"TimeSlot\" ts on l.time_slot_id = ts.id where u.id = ? and l.lesson_date <= cast(current_timestamp as date) and (l.is_cancel = false or l.is_cancel is null)";

        return jdbcTemplate.queryForObject(LessonDaoQueries.getLessonCountTillTodayByStudent, new Object[] {userId}, Integer.class);
    }

    @Override
    public int getLessonCountInCourseTillToday(int courseId) {

//        String SQL = "select count(l.id) from \"Lesson\" l join \"Course\" c on l.course_id = c.id where c.id = ? and l.lesson_date <= cast(current_timestamp as date) and (l.is_cancel = false or l.is_cancel is null)";

        return jdbcTemplate.queryForObject(LessonDaoQueries.getLessonCountInCourseTillToday, new Object[] {courseId}, Integer.class);
    }

    @Override
    public int getLessonCountInCourse(int courseId) {
//        String SQL = "select count(l.id) from \"Lesson\" l join \"Course\" c on l.course_id = c.id where c.id = ?";

        return jdbcTemplate.queryForObject(LessonDaoQueries.getLessonCountInCourse, new Object[] {courseId}, Integer.class);
    }
}
