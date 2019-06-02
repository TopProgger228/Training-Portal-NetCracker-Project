package com.group3.basic.netcracker.backend.util.dtomapper;

import com.group3.basic.netcracker.backend.dto.UserAttendanceDto;
import com.group3.basic.netcracker.backend.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserAttendanceDtoMapper {

    public UserAttendanceDto toUserAttendanceDto(User user) {

        UserAttendanceDto uad = new UserAttendanceDto();
        uad.setId(user.getId());
        uad.setUserFirstName(user.getFname());
        uad.setUserLastName(user.getLname());

        return uad;
    }

}
