package com.group3.basic.netcracker.backend.util.dtomapper;

import com.group3.basic.netcracker.backend.dao.LessonDao;
import com.group3.basic.netcracker.backend.dto.CourseForTrainerDto;
import com.group3.basic.netcracker.backend.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseForTrainerDtoMapper {

    private LessonDao lessonDao;

    @Autowired
    public CourseForTrainerDtoMapper(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    public CourseForTrainerDto toCourseForTrainerDtoMapper(Course course) {
        CourseForTrainerDto courseForTrainerDto = new CourseForTrainerDto();
        courseForTrainerDto.setCourseId(course.getId());
        courseForTrainerDto.setName(course.getName());
        courseForTrainerDto.setSkillLevel(course.getSkill_level());
        courseForTrainerDto.setTotalLessonCount(lessonDao.getLessonCountInCourse(course.getId()));

        return courseForTrainerDto;
    }


}
