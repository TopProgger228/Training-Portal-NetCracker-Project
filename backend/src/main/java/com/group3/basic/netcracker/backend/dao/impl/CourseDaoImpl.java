package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.CourseDao;
import com.group3.basic.netcracker.backend.entity.Course;
import com.group3.basic.netcracker.backend.util.rowmapper.CourseIdRowMapper;
import com.group3.basic.netcracker.backend.util.rowmapper.CourseRowMapper;
import com.group3.basic.netcracker.backend.util.rowmapper.CourseWithTrainerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public class CourseDaoImpl implements CourseDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createCourse(String name, LocalDate start_date, LocalDate end_date, String info, String skill_level, int trainer_id, int qty_per_week) {
        String SQL = "INSERT INTO \"Course\" (name, start_date, end_date, info, skill_level, trainer_id, qty_per_week) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(SQL, name, start_date, end_date, info, skill_level, trainer_id, qty_per_week);
    }

    @Override
    public Course getCourseById(int id) {
        String SQL = "SELECT * FROM \"Course\" WHERE id = ?";
        Course course = (Course) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new CourseRowMapper());
        return course;
    }

    @Override
    public Integer getIdByCourseName(String name) {
        String SQL = "SELECT id FROM \"Course\" WHERE name = ?";
        Course courseId = (Course) jdbcTemplate.queryForObject(SQL, new Object[]{name}, new CourseIdRowMapper());
        return courseId.getId();
    }

    @Override
    public Course getCourseByName(String name) {
        String SQL = "SELECT name, start_date, end_date, info, skill_level, trainer_id, qty_per_week, id FROM \"Course\" WHERE name = '" + name + "'";
        Course course = (Course) jdbcTemplate.query(SQL, new CourseRowMapper()).get(0);

        return course;
    }

    @Override
    public List listCourses() {
        String SQL = "SELECT id, name, info, trainer_id, skill_level, start_date, end_date, qty_per_week FROM \"Course\"";
        return jdbcTemplate.query(SQL, new CourseRowMapper());
    }

    @Override
    public List listActiveCourses() {
        String SQL = "SELECT id, name, info, trainer_id, skill_level, start_date, end_date, qty_per_week FROM \"Course\" WHERE start_date > ? ORDER BY id DESC;";
        List courses = jdbcTemplate.query(SQL, new Object[]{new java.sql.Date(System.currentTimeMillis())}, new CourseRowMapper());
        return courses;
    }

    @Override
    public List listCoursesByUsername(String username) {
        String SQL = "SELECT id, name, info, trainer_id, skill_level, start_date, end_date, qty_per_week FROM \"Course\" " +
                "where trainer_id=(select id from \"User\" where username='" + username + "')";
        List courses = jdbcTemplate.query(SQL, new CourseRowMapper());
        return courses;
    }

    @Override
    public void removeCourse(int id) {
        String SQL = "DELETE FROM \"Course\" WHERE id = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public void updateCourse(int id, String name, LocalDate start_date, LocalDate end_date, String info,
                             String skill_level, int trainer_id, int qty_per_week) {
        String SQL = "UPDATE \"Course\" SET name = ?, start_date = ?, end_date = ?, info = ?, skill_level = ?, trainer_id = ?, qty_per_week = ? WHERE id = ?";
        jdbcTemplate.update(SQL, name, start_date, end_date, info, skill_level, trainer_id, qty_per_week, id);
    }

    @Override
    public Course getCourseByLesson(int lessonId) {

        String SQL = "select c.id, c.name, c.info, c.trainer_id, c.skill_level, c.start_date, c.end_date, c.qty_per_week from \"Course\" c join \"Lesson\" l on c.id = l.course_id where  l.id = ?";

        return (Course) jdbcTemplate.queryForObject(SQL, new Object[]{lessonId}, new CourseRowMapper());

    }


    @Override
    public List<Course> getCourseByUserUsername(String username) {

        String SQL = "select c.id, c.name, c.info, c.trainer_id, c.skill_level, c.start_date, c.end_date, c.qty_per_week from \"Course\" c join \"Group\" g on c.id = g.course_id join \"User\" u on g.user_id = u.id where u.username = ?";

        return jdbcTemplate.query(SQL, new Object[]{username}, new CourseRowMapper());
    }

    @Override
    public List<Course> getCourseByUserId(int userId) {

        String SQL = "select c.id, c.name, c.info, c.trainer_id, c.skill_level, c.start_date, c.end_date, c.qty_per_week from \"Course\" c join \"Group\" g on c.id = g.course_id join \"User\" u on g.user_id = u.id where u.id = ?";

        return jdbcTemplate.query(SQL, new Object[]{userId}, new CourseRowMapper());
    }

    @Override
    public List<Course> getCourseByTrainerUsername(String username) {

        String SQL = "select c.id, c.name, c.info, c.trainer_id, c.skill_level, c.start_date, c.end_date, c.qty_per_week from \"Course\" c join \"User\" t on c.trainer_id = t.id where t.username = ?";

        return jdbcTemplate.query(SQL, new Object[]{username}, new CourseRowMapper());

    }

    @Override
    public List<Course> getCourseBySkillLevel(String level) {

        String SQL = "select c.id, c.name, c.info, c.trainer_id, c.skill_level, c.start_date, c.end_date, c.qty_per_week from \"Course\" c where c.skill_level = ?";

        return jdbcTemplate.query(SQL, new Object[]{level}, new CourseRowMapper());
    }

    public List<CourseWithTrainerRowMapper> getCoursesWithTrainerByUsername(String username) {

        String SQL = "select c.name, c.info, c.skill_level, c.start_date, c.end_date, c.qty_per_week, u.username, u.fname, u.lname, u.email from (((\"Course\" c join \"Group\" g on c.id=g.course_id) join \"User\" u on c.trainer_id=u.id) join \"User\" us on g.user_id=us.id) where us.username= ?";

        return jdbcTemplate.query(SQL, new Object[]{username}, new CourseWithTrainerRowMapper());
    }
}