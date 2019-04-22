package com.group3.basic.netcracker.backend.course;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface CourseServiceInterface{

void createCourse(String name, String info, int userId, String skillLevel, String learnDirection, LocalDate startDate, LocalDate endDate, int qtyPerWeek);

Course getCourseById(int id);

List<Course> courses();

void removeCourse(int id);

void updateCourse(String name, String info, int userId, String skillLevel, String learnDirection, LocalDate startDate, LocalDate endDate, int qtyPerWeek, int id);
}
