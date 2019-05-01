package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.dto.CheckLessonAttendanceDto;

public interface CheckAttendanceService {

    CheckLessonAttendanceDto getFullCheckAttendance (int lessonId);

    void changeLessonMissing (int userId, int lessonId, String reason);


}
