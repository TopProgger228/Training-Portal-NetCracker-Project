package com.group3.basic.netcracker.backend.util.dtomapper;

import com.group3.basic.netcracker.backend.dao.CourseDao;
import com.group3.basic.netcracker.backend.dao.TimeSlotDao;
import com.group3.basic.netcracker.backend.dto.LessonAttendanceDto;
import com.group3.basic.netcracker.backend.entity.Lesson;
import com.group3.basic.netcracker.backend.entity.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LessonAttendanceDtoMapper {

    private final TimeSlotDao timeSlotDao;
    private final CourseDao courseDao;

    @Autowired
    public LessonAttendanceDtoMapper(TimeSlotDao timeSlotDao, CourseDao courseDao) {
        this.timeSlotDao = timeSlotDao;
        this.courseDao = courseDao;
    }

    public LessonAttendanceDto toLessonAttendanceDto(Lesson lesson) {
        TimeSlot timeSlot = timeSlotDao.getTimeSlotByLessonId(lesson.getLessonId());
        LessonAttendanceDto lad = new LessonAttendanceDto();
        lad.setLessonId(lesson.getLessonId());
        lad.setCancel(lesson.isCancel());
        lad.setLessonDate(lesson.getLessonDate());
        lad.setStartTime(timeSlot.getStartTime());
        lad.setEndTime(timeSlot.getEndTime());
        lad.setCourseName(courseDao.getCourseByLesson(lesson.getLessonId()).getName());

        return lad;
    }

}