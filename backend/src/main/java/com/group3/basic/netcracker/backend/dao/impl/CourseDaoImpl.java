package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.CourseDAO;
import com.group3.basic.netcracker.backend.entity.Course;
import com.group3.basic.netcracker.backend.util.rowmapper.CourseRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public class CourseDaoImpl implements CourseDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createCourse(String name, LocalDate start_date, LocalDate end_date, String info, String skill_level, int user_id, int qty_per_week) {
        String SQL = "INSERT INTO \"Course\" (name, start_date, end_date, info, skill_level, user_id, qty_per_week) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(SQL, name, start_date, end_date, info, skill_level, user_id, qty_per_week);
    }

    @Override
    public Course getCourseById(int id) {
        String SQL = "SELECT * FROM \"Course\" WHERE id = ?";
        Course course= (Course) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new CourseRowMapper());
        return course;
    }

    @Override
    public List listCourses() {
        String SQL = "SELECT id, name, info, user_id, skill_level, start_date, end_date, qty_per_week FROM \"Course\"";
        List courses = jdbcTemplate.query(SQL, new CourseRowMapper());
        return courses;
    }

    @Override
    public List listCoursesByUsername(String username) {
        String SQL = "SELECT id, name, info, user_id, skill_level, start_date, end_date, qty_per_week FROM \"Course\" " +
                "where user_id=(select id from \"User\" where username='" + username + "')";
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
                             String skill_level, int user_id, int qty_per_week) {
        String SQL = "UPDATE \"Course\" SET name = ?, start_date = ?, end_date = ?, info = ?, skill_level = ?, user_id = ?, qty_per_week = ? WHERE id = ?";
        jdbcTemplate.update(SQL, name, start_date, end_date, info, skill_level, user_id, qty_per_week, id);
    }

}
