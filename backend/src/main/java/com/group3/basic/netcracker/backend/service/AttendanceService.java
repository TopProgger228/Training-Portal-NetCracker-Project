package com.group3.basic.netcracker.backend.service;

import java.util.List;

import com.group3.basic.netcracker.backend.dto.CourseAttendanceDto;
import com.group3.basic.netcracker.backend.dto.LessonAttendanceDto;
import com.group3.basic.netcracker.backend.dto.UserAttendanceDto;
import com.group3.basic.netcracker.backend.entity.Attendance;

public interface AttendanceService {
	
	List<Attendance> listAttendance();

	List<CourseAttendanceDto> getAllCourseAttendance();

	List<LessonAttendanceDto> getLessonsOfCourseAttendance(int courseId);

	List<UserAttendanceDto> getUsersOfCourseAttendance(int lessonId);

}
