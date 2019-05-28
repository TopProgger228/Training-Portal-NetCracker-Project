package com.group3.basic.netcracker.backend.util.dtomapper;

import com.group3.basic.netcracker.backend.dao.TimeSlotDao;
import com.group3.basic.netcracker.backend.dto.LessonDto;
import com.group3.basic.netcracker.backend.entity.Lesson;
import com.group3.basic.netcracker.backend.entity.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LessonDtoMapper {

    private TimeSlotDao timeSlotDao;

    @Autowired
    public LessonDtoMapper(TimeSlotDao timeSlotDao) {
        this.timeSlotDao = timeSlotDao;
    }

    public LessonDto toLessonDto (Lesson lesson) {
        LessonDto lessonDto = new LessonDto();
        TimeSlot timeSlot = timeSlotDao.getTimeSlotByLessonId(lesson.getLessonId());
        lessonDto.setLessonId(lesson.getLessonId());
        lessonDto.setLessonDate(lesson.getLessonDate());
        lessonDto.setCancel(lesson.isCancel());
        lessonDto.setStartTime(timeSlot.getStartTime());
        lessonDto.setEndTime(timeSlot.getEndTime());

        return lessonDto;
    }

}
