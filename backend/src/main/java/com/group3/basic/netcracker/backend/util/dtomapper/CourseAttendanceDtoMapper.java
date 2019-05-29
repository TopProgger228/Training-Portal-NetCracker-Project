package com.group3.basic.netcracker.backend.util.dtomapper;

import com.group3.basic.netcracker.backend.dao.LessonDao;
import com.group3.basic.netcracker.backend.dao.UserDao;
import com.group3.basic.netcracker.backend.dto.CourseAttendanceDto;
import com.group3.basic.netcracker.backend.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseAttendanceDtoMapper {

    private final TrainerAttendanceDtoMapper trainerAttendanceDtoMapper;
    private final LessonDao lessonDao;
    private final UserDao userDao;

    @Autowired
    public CourseAttendanceDtoMapper(TrainerAttendanceDtoMapper trainerAttendanceDtoMapper, LessonDao lessonDao, UserDao userDao) {
        this.trainerAttendanceDtoMapper = trainerAttendanceDtoMapper;
        this.lessonDao = lessonDao;
        this.userDao = userDao;
    }

    public CourseAttendanceDto toCourseAttendanceDto (Course course) {

        CourseAttendanceDto cad = new CourseAttendanceDto();
        cad.setCourseId(course.getId());
        cad.setName(course.getName());
        cad.setSkillLevel(course.getSkill_level());
        cad.setTrainer(trainerAttendanceDtoMapper.toTrainerAttendanceDto(userDao.getTrainerByCourse(course.getId())));
        cad.setTotalLessonCount(lessonDao.getLessonCountInCourse(course.getId()));

        return cad;

    }

}
