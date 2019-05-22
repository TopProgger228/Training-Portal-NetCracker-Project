package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.dto.CheckLessonAttendanceDto;
import com.group3.basic.netcracker.backend.dto.LessonAttendanceDto;

import java.util.List;

public interface CheckAttendanceService {

    List<LessonAttendanceDto> getTodayLessonsByTrainer(int trainerId);

    CheckLessonAttendanceDto getFullCheckAttendance(int lessonId);

    void changeLessonMissing(int userId, int lessonId, String reason);

    List<LessonAttendanceDto> getTodayLessonsByTrainerUsername(String username);
}
