package com.group3.basic.netcracker.backend.util.dtomapper;

import com.group3.basic.netcracker.backend.dto.TrainerAttendanceDto;
import com.group3.basic.netcracker.backend.entity.User;
import org.springframework.stereotype.Service;

@Service
public class TrainerAttendanceDtoMapper {

    public TrainerAttendanceDto toTrainerAttendanceDto (User user) {

        TrainerAttendanceDto tad = new TrainerAttendanceDto();
        tad.setId(user.getId());
        tad.setFirstName(user.getFname());
        tad.setLastName(user.getLname());
        return tad;

    }


    public User toUser (TrainerAttendanceDto tad) {

        User user = new User();
        user.setId(tad.getId());
        user.setFname(tad.getFirstName());
        user.setLname(tad.getLastName());
        return user;

    }

}
