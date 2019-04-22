package com.group3.basic.netcracker.backend.course;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseServiceImpl implements CourseServiceInterface {
	private final CourseDao courseDao;

	@Autowired
	public CourseServiceImpl(CourseDao courseDao) {
		this.courseDao = courseDao;
	}
	@Override
	public void createCourse(String name, String info, int userId, String skillLevel, String learnDirection, LocalDate startDate, LocalDate endDate, int qtyPerWeek) {
		courseDao.createCourse(name, info,  userId, skillLevel,  learnDirection, startDate, endDate, qtyPerWeek);
	}

	@Override
	public Course getCourseById(int id) {
		return courseDao.getCourseById(id);
	}

	@Override
	public List<Course> courses() {
	return courseDao.listCourses();
	}

	@Override
	public void removeCourse(int id) {
	courseDao.removeCourse(id);
	}

	@Override
	public void updateCourse(String name, String info, int userId, String skillLevel, String learnDirection, LocalDate startDate, LocalDate endDate, int qtyPerWeek, int id) {
     courseDao.updateCourse(name, info, userId, skillLevel,  learnDirection, startDate,  endDate, qtyPerWeek, id);
	}
}
	