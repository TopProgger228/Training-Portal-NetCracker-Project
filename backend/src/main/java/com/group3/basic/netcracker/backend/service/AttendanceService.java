package com.group3.basic.netcracker.backend.service;

import java.util.List;

import com.group3.basic.netcracker.backend.dto.*;
import com.group3.basic.netcracker.backend.entity.Attendance;

public interface AttendanceService {
	
	List<Attendance> listAttendance();

	List<CourseAttendanceDto> getAllCourseAttendance();

	List<LessonAttendanceDto> getLessonsOfCourseAttendance(int courseId);

	List<LessonAttendanceDto> getLessonsOfCourseAttendanceByUser(int courseId, int userId);

	List<UserAttendanceDto> getUsersOfCourseAttendance(int lessonId);

	List<CourseAttendanceDto> getCourseAttendanceByUser (String username);

    List<CourseAttendanceDto> getCourseAttendanceByUserId (int userId);

	List<TrainerSelectorDto> getTrainerForSelector();

	List<CourseAttendanceDto> getCourseAttendanceByTrainer(int id);

	List<CourseAttendanceDto> getCourseAttendanceBySkillLevel(String level);

	List<StudentAttendanceForManagerDto> getStudentAttendanceForManagerDto(String userName);

}
