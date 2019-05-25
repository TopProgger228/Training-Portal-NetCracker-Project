package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.entity.Course;
import com.group3.basic.netcracker.backend.util.rowmapper.CourseWithTrainerRowMapper;

import java.time.LocalDate;
import java.util.List;

public interface CourseService {
    void createCourse(String name, LocalDate start_date, LocalDate end_date, String info,
                      String skill_level, int trainer_id, int qty_per_week);

    Course getCourseById(int id);

    Integer getIdByCourseName(String name);

    List listCourses();

    List listActiveCourses();

    List listCoursesByUsername(String username);

    void removeCourse(int id);

    void updateCourse(int id, String name, LocalDate start_date, LocalDate end_date, String info,
                      String skill_level, int trainer_id, int qty_per_week);

    Course getCourseByName(String name);

    List<CourseWithTrainerRowMapper> getCoursesWithTrainerByUsername(String username);

}