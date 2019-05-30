package com.group3.basic.netcracker.backend.service.impl;

import com.group3.basic.netcracker.backend.dao.*;
import com.group3.basic.netcracker.backend.dto.AttendanceStatus;
import com.group3.basic.netcracker.backend.dto.CheckLessonAttendanceDto;
import com.group3.basic.netcracker.backend.dto.LessonAttendanceDto;
import com.group3.basic.netcracker.backend.dto.UserAttendanceDto;
import com.group3.basic.netcracker.backend.entity.Lesson;
import com.group3.basic.netcracker.backend.entity.LessonMissing;
import com.group3.basic.netcracker.backend.entity.User;
import com.group3.basic.netcracker.backend.service.CheckAttendanceService;
import com.group3.basic.netcracker.backend.util.dtomapper.LessonAttendanceDtoMapper;
import com.group3.basic.netcracker.backend.util.dtomapper.UserAttendanceDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CheckAttendanceServiceImpl implements CheckAttendanceService {

    private final LessonDao lessonDao;
    private final UserDao userDao;
    private final LessonMissingDao lessonMissingDao;
    private final UserAttendanceDtoMapper userAttendanceDtoMapper;
    private final LessonAttendanceDtoMapper lessonAttendanceDtoMapper;

    @Autowired
    public CheckAttendanceServiceImpl(LessonDao lessonDao, UserDao userDao, LessonMissingDao lessonMissingDao,
                                      UserAttendanceDtoMapper userAttendanceDtoMapper,
                                      LessonAttendanceDtoMapper lessonAttendanceDtoMapper) {
        this.lessonDao = lessonDao;
        this.userDao = userDao;
        this.lessonMissingDao = lessonMissingDao;
        this.userAttendanceDtoMapper = userAttendanceDtoMapper;
        this.lessonAttendanceDtoMapper = lessonAttendanceDtoMapper;
    }

    @Override
    public CheckLessonAttendanceDto getFullCheckAttendance(int lessonId) {
        final Lesson lesson = lessonDao.getLessonById(lessonId);

        return lessonAttendanceDtoMapper.toCheckLessonAttendanceDto(lesson);
    }

    @Override
    public List<UserAttendanceDto> getUsersByLessonId(int lessonId) {
        final List<User> userList = userDao.getUsersByLesson(lessonId);

        return userList.stream().map(userAttendanceDtoMapper::toUserAttendanceDto).collect(Collectors.toList());
    }

    @Override
    public void changeLessonMissing(int userId, int lessonId, final String reason) {

        final Optional<LessonMissing> lessonMissing = Optional.ofNullable(lessonMissingDao
                .getLessonMissingByLessonIdAndUserId(lessonId, userId));
        if (reason.equals(AttendanceStatus.PRESENT.toString())) {
            if (lessonMissing.isPresent()) {
                lessonMissingDao.delete(userId, lessonId);
            }
        } else {
            if (lessonMissing.isPresent()) {
                lessonMissingDao.updateReason(userId, lessonId, reason);
            } else {
                lessonMissingDao.add(userId, lessonId, reason);
            }
        }
    }

    @Override
    public List<LessonAttendanceDto> getTodayLessonsByTrainerUsername(final String username) {
        final List<Lesson> lessonList = lessonDao.getTodayLessonsByTrainerUsername(username);

        return lessonList.stream().map(lessonAttendanceDtoMapper::toLessonAttendanceDto).collect(Collectors.toList());
    }

}
