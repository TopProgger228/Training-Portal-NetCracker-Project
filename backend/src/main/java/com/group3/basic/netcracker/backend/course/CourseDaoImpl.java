package com.group3.basic.netcracker.backend.course;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.group3.basic.netcracker.backend.timeslot.TimeSlot;
import com.group3.basic.netcracker.backend.timeslot.TimeSlotDao;
import com.group3.basic.netcracker.backend.timeslot.TimeSlotRowMapper;

@Transactional
	@Repository
	public class CourseDaoImpl implements CourseDao {
	    private final JdbcTemplate jdbcTemplate;

	    @Autowired
	    public CourseDaoImpl(JdbcTemplate jdbcTemplate){
	        this.jdbcTemplate = jdbcTemplate;
	    }

	    @Override
	    public Course getCourseById(int id) {
	        String SQL = "SELECT * FROM \"Course\" WHERE id = ?";
	        Course course = (Course) jdbcTemplate.queryForObject(SQL, new Object[]{id}, new CourseRowMapper());
	        return course;
	    }

	    public Course findByStartDate(Course startDate) {
	        String SQL = "SELECT * FROM \"Course\" WHERE start_date = ?";
	        Course course = (Course) jdbcTemplate.queryForObject(SQL, new Object[]{startDate}, new CourseRowMapper());
	        return course;
	    }

	    @Override
	    public List listCourses() {
	        String SQL = "SELECT * FROM \"Course\"";
	        List courses = jdbcTemplate.query(SQL, new CourseRowMapper());
	        return courses;
	    }

	    @Override
	    public void removeCourse(int id) {
	        String SQL = "DELETE FROM \"Course\" WHERE id = ?";
	        jdbcTemplate.update(SQL, id);
	        System.out.println("Course removed");
	    }

	    @Override
	    public void updateCourse(String name, String info, int userId, String skillLevel, String learnDirection, LocalDate startDate, LocalDate endDate, int qtyPerWeek, int id) {
	        String SQL = "UPDATE \"Course\" SET name = ?, info = ?, user_id = ?, skill_level = ?, start_date = ?, end_date = ?, qty_per_week = ? WHERE id = ?";
	        jdbcTemplate.update(SQL, name, info, userId, skillLevel, learnDirection, startDate, endDate, qtyPerWeek);
	        System.out.println("Course updated.");
	    }
	    @Override
		public void createCourse(String name, String info, int userId, String skillLevel, String learnDirection, LocalDate startDate, LocalDate endDate, int qtyPerWeek) {
			 String SQL = "INSERT INTO \"Course\" (name, info, user_id, skill_level, learn_direction, start_date, end_date, qty_per_week) VALUES (?,?,?,?,?,?,?,?)";
		        jdbcTemplate.update(SQL,  name,  info, userId, skillLevel,  learnDirection, startDate,  endDate, qtyPerWeek);
		        System.out.println("Course created.");
		}

}
	