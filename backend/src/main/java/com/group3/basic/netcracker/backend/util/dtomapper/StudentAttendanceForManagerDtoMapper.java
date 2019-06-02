package com.group3.basic.netcracker.backend.util.dtomapper;

import com.group3.basic.netcracker.backend.dto.StudentAttendanceForManagerDto;
import com.group3.basic.netcracker.backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class StudentAttendanceForManagerDtoMapper {

    public StudentAttendanceForManagerDto toStudentAttendanceForManagerDto(User user) {

        StudentAttendanceForManagerDto tmp = new StudentAttendanceForManagerDto();
        tmp.setId(user.getId());
        tmp.setUserName(user.getUsername());
        tmp.setFirstName(user.getFname());
        tmp.setLastName(user.getLname());
        return tmp;

    }

    public User toUser(StudentAttendanceForManagerDto tmp) {

        User user = new User();
        user.setId(tmp.getId());
        user.setUsername(tmp.getUserName());
        user.setFname(tmp.getFirstName());
        user.setLname(tmp.getLastName());
        return user;

    }

}
