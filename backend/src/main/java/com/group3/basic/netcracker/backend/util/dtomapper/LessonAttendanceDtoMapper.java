package com.group3.basic.netcracker.backend.util.dtomapper;

import com.group3.basic.netcracker.backend.dto.LessonAttendanceDto;
import com.group3.basic.netcracker.backend.entity.Lesson;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class LessonAttendanceDtoMapper {

    public Lesson toLesson(LessonAttendanceDto lad) {

        Lesson lesson = new Lesson();
        lesson.setLessonId(lad.getLessonId());
        lesson.setLessonDate(lad.getStartDateTime());
        lesson.setCancel(lad.isCancel());

        return lesson;
    }

    public LessonAttendanceDto toLessonAttendanceDto(Lesson lesson) {

        LessonAttendanceDto lad = new LessonAttendanceDto();
        lad.setLessonId(lesson.getLessonId());
        lad.setCancel(lesson.isCancel());
        lad.setStartDateTime(lesson.getLessonDate());

        return lad;
    }

}
