package com.group3.basic.netcracker.backend.util.dtomapper;

import com.group3.basic.netcracker.backend.dto.CourseAttendanceDto;
import com.group3.basic.netcracker.backend.entity.Course;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CourseAttendanceDtoMapper {

    public CourseAttendanceDto toCourseAttendanceDto (Course course) {

        CourseAttendanceDto cad = new CourseAttendanceDto();
        cad.setCourseId(course.getId());
        cad.setName(course.getName());
        cad.setSkillLevel(course.getSkill_level());

        return cad;

    }

}
