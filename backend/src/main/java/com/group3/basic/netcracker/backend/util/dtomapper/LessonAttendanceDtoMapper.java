package com.group3.basic.netcracker.backend.util.dtomapper;

import com.group3.basic.netcracker.backend.dto.LessonAttendanceDto;
import com.group3.basic.netcracker.backend.entity.Lesson;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class LessonAttendanceDtoMapper {

    public LessonAttendanceDto toLessonAttendanceDto(Lesson lesson) {

        LessonAttendanceDto lad = new LessonAttendanceDto();
        lad.setLessonId(lesson.getLessonId());
        lad.setCancel(lesson.isCancel());
        lad.setStartDateTime(lesson.getLessonDate());

        return lad;
    }

}
