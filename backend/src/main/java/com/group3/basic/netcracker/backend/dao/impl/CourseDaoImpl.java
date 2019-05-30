package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.CourseDao;
import com.group3.basic.netcracker.backend.entity.Course;
import com.group3.basic.netcracker.backend.util.rowmapper.CourseIdRowMapper;
import com.group3.basic.netcracker.backend.util.rowmapper.CourseRowMapper;
import com.group3.basic.netcracker.backend.util.rowmapper.CourseWithChoosenRowMapper;
import com.group3.basic.netcracker.backend.util.rowmapper.CourseWithTrainerRowMapper;
import com.group3.basic.netcracker.backend.util.sql.CourseDaoQueries;
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
    public void createCourse(String name, LocalDate start_date, LocalDate end_date,
                             String info, String skill_level, int trainer_id, int qty_per_week) {
        jdbcTemplate.update(CourseDaoQueries.createCourseQuery,
                name, start_date, end_date, info, skill_level, trainer_id, qty_per_week);
    }

    @Override
    public Course getCourseById(int id) {
        return (Course) jdbcTemplate.queryForObject(CourseDaoQueries.getCourseById,
                new Object[]{id}, new CourseRowMapper());
    }

    @Override
    public Integer getIdByCourseName(String name) {
        Course courseId = (Course) jdbcTemplate.queryForObject(CourseDaoQueries.getIdByCourseNameQuery,
                new Object[]{name}, new CourseIdRowMapper());
        return courseId.getId();
    }

    @Override
    public Course getCourseByName(String name) {
        return (Course) jdbcTemplate.query(CourseDaoQueries.getCourseByName, new CourseRowMapper(), name).get(0);
    }

    @Override
    public List<Course> listCourses() {
        return jdbcTemplate.query(CourseDaoQueries.getCoursesList, new CourseRowMapper());
    }

    @Override
    public List listActiveCourses() {
        return jdbcTemplate.query(CourseDaoQueries.getActiveCoursesList,
                new Object[]{new java.sql.Date(System.currentTimeMillis())}, new CourseWithChoosenRowMapper());
    }

    @Override
    public List listCoursesByUsername(String username) {
        return jdbcTemplate.query(CourseDaoQueries.getListCoursesByUsername, new CourseRowMapper(), username);
    }

    @Override
    public void removeCourse(int id) {
        jdbcTemplate.update(CourseDaoQueries.removeCourse, id);
    }

    @Override
    public void updateCourse(int id, String name, LocalDate start_date, LocalDate end_date, String info,
                             String skill_level, int trainer_id, int qty_per_week) {
        jdbcTemplate.update(CourseDaoQueries.updateCourse,
                name, start_date, end_date, info, skill_level, trainer_id, qty_per_week, id);
    }

    @Override
    public Course getCourseByLesson(int lessonId) {
        return (Course) jdbcTemplate.queryForObject(CourseDaoQueries.getCourseByLessonQuery,
                new Object[]{lessonId}, new CourseRowMapper());
    }


    @Override
    public List<Course> getCourseByUserUsername(String username) {
        return jdbcTemplate.query(CourseDaoQueries.getCourseByUserUsernameQuery,
                new Object[]{username}, new CourseRowMapper());
    }

    @Override
    public List<Course> getCourseByUserId(int userId) {
        return jdbcTemplate.query(CourseDaoQueries.getCourseByUserId, new Object[]{userId}, new CourseRowMapper());
    }

    @Override
    public List<Course> getCourseByTrainerUsername(String username) {
        return jdbcTemplate.query(CourseDaoQueries.getCourseByTrainerUsername,
                new Object[]{username}, new CourseRowMapper());
    }

    @Override
    public List<Course> getCourseBySkillLevel(String level) {
        return jdbcTemplate.query(CourseDaoQueries.getCourseBySkillLevel,
                new Object[]{level}, new CourseRowMapper());
    }

    public List<CourseWithTrainerRowMapper> getCoursesWithTrainerByUsername(String username) {
        return jdbcTemplate.query(CourseDaoQueries.getCoursesWithTrainerByUsername,
                new Object[]{username}, new CourseWithTrainerRowMapper());
    }
}