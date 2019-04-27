package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.entity.Course;

import java.time.LocalDate;
import java.util.List;

public interface CourseService {
    void createCourse(String name, LocalDate start_date, LocalDate end_date, String info,
                    String skill_level, String learn_direction, int user_id, int qty_per_week);

    Course getCourseById(int id);

    List listCourses();

    void removeCourse(int id);

    void updateCourse(int id, String name, LocalDate start_date, LocalDate end_date, String info,
                      String skill_level, String learn_direction, int user_id, int qty_per_week);
}