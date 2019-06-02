package com.group3.basic.netcracker.backend.service;

import java.util.List;

import com.group3.basic.netcracker.backend.dto.*;

public interface AttendanceService {

    List<CourseAttendanceDto> getAllCourseAttendance();

    List<LessonAttendanceDto> getLessonsOfCourseAttendance(int courseId);

    List<LessonAttendanceDto> getLessonsOfCourseAttendanceByUser(int courseId, int userId);

    List<UserAttendanceDto> getUsersOfCourseAttendance(int lessonId);

    List<CourseAttendanceDto> getCourseAttendanceByUser(String username);

    List<CourseAttendanceDto> getCourseAttendanceByUserId(int userId);

    List<CourseAttendanceDto> getCourseAttendanceByTrainerUsername(String username);

    List<CourseAttendanceDto> getCourseAttendanceBySkillLevel(String level);

    List<StudentAttendanceForManagerDto> getStudentAttendanceForManagerDto(String userName);

}
