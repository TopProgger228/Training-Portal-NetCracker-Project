package com.group3.basic.netcracker.backend.course;

import java.time.LocalDate;
import java.util.List;

import com.group3.basic.netcracker.backend.course.Course;

public interface CourseDao {
	Course getCourseById(int id);
    List<Course> listCourses();
    void removeCourse(int id);
	void createCourse(String name, String info, int userId, String skillLevel, String learnDirection, LocalDate startDate, LocalDate endDate, int qtyPerWeek);
	void updateCourse(String name, String info, int userId, String skillLevel, String learnDirection, LocalDate startDate, LocalDate endDate, int qtyPerWeek, int id);
}
