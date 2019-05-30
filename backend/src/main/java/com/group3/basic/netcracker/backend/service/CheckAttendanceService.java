package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.dto.CheckLessonAttendanceDto;
import com.group3.basic.netcracker.backend.dto.LessonAttendanceDto;
import com.group3.basic.netcracker.backend.dto.UserAttendanceDto;

import java.util.List;

public interface CheckAttendanceService {

    CheckLessonAttendanceDto getFullCheckAttendance(int lessonId);

    List<UserAttendanceDto> getUsersByLessonId(int lessonId);

    void changeLessonMissing(int userId, int lessonId, String reason);

    List<LessonAttendanceDto> getTodayLessonsByTrainerUsername(String username);
}
