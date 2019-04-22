package com.group3.basic.netcracker.backend.course;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CourseRowMapper implements RowMapper {
	@Override
	public Course mapRow(ResultSet row, int rowNum) throws SQLException {
         Course course = new Course();
         course.setName(row.getString("name"));
         course.setInfo(row.getString("info"));
         course.setUserId(row.getInt("user_id"));
         course.setSkillLevel(row.getString("skill_level"));
         course.setLearnDirection(row.getString("learn_direction"));
         course.setStartDate(row.getDate("start_date"));
         course.setEndDate(row.getDate("end_date"));
         course.setQtyPerWeek(row.getInt("qty_per_week"));
		return course;
	}
}
