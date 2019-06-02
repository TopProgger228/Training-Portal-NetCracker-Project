package com.group3.basic.netcracker.backend.util.dtomapper;

import com.group3.basic.netcracker.backend.dao.CourseDao;
import com.group3.basic.netcracker.backend.dao.TimeSlotDao;
import com.group3.basic.netcracker.backend.dao.UserDao;
import com.group3.basic.netcracker.backend.dto.CheckLessonAttendanceDto;
import com.group3.basic.netcracker.backend.dto.LessonAttendanceDto;
import com.group3.basic.netcracker.backend.entity.Course;
import com.group3.basic.netcracker.backend.entity.Lesson;
import com.group3.basic.netcracker.backend.entity.TimeSlot;
import com.group3.basic.netcracker.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LessonAttendanceDtoMapper {

    private final TimeSlotDao timeSlotDao;
    private final CourseDao courseDao;
    private final UserDao userDao;
    private final TrainerAttendanceDtoMapper trainerAttendanceDtoMapper;

    @Autowired
    public LessonAttendanceDtoMapper(TimeSlotDao timeSlotDao, CourseDao courseDao, UserDao userDao, TrainerAttendanceDtoMapper trainerAttendanceDtoMapper) {
        this.timeSlotDao = timeSlotDao;
        this.courseDao = courseDao;
        this.userDao = userDao;
        this.trainerAttendanceDtoMapper = trainerAttendanceDtoMapper;
    }

    public LessonAttendanceDto toLessonAttendanceDto(Lesson lesson) {
        TimeSlot timeSlot = timeSlotDao.getTimeSlotByLessonId(lesson.getLessonId());
        LessonAttendanceDto lessonAttendanceDto = new LessonAttendanceDto();
        lessonAttendanceDto.setLessonId(lesson.getLessonId());
        lessonAttendanceDto.setCancel(lesson.isCancel());
        lessonAttendanceDto.setLessonDate(lesson.getLessonDate());
        lessonAttendanceDto.setStartTime(timeSlot.getStartTime());
        lessonAttendanceDto.setEndTime(timeSlot.getEndTime());
        lessonAttendanceDto.setCourseName(courseDao.getCourseByLesson(lesson.getLessonId()).getName());

        return lessonAttendanceDto;
    }

    public CheckLessonAttendanceDto toCheckLessonAttendanceDto(Lesson lesson) {
        CheckLessonAttendanceDto checkLessonAttendanceDto = new CheckLessonAttendanceDto();
        Course course = courseDao.getCourseByLesson(lesson.getLessonId());
        User trainer = userDao.getTrainerByCourse(course.getId());
        checkLessonAttendanceDto.setLessonId(lesson.getLessonId());
        checkLessonAttendanceDto.setCourseName(course.getName());
        checkLessonAttendanceDto.setLessonDate(lesson.getLessonDate());
        checkLessonAttendanceDto.setCancel(lesson.isCancel());
        checkLessonAttendanceDto.setTrainer(trainerAttendanceDtoMapper.toTrainerAttendanceDto(trainer));

        return checkLessonAttendanceDto;
    }

}
