package com.group3.basic.netcracker.backend.service.impl;

import com.group3.basic.netcracker.backend.dao.CourseDAO;
import com.group3.basic.netcracker.backend.dao.LessonDao;
import com.group3.basic.netcracker.backend.dao.LessonMissingDao;
import com.group3.basic.netcracker.backend.dao.UserDao;
import com.group3.basic.netcracker.backend.dto.CheckLessonAttendanceDto;
import com.group3.basic.netcracker.backend.dto.LessonAttendanceDto;
import com.group3.basic.netcracker.backend.dto.UserAttendanceDto;
import com.group3.basic.netcracker.backend.entity.Course;
import com.group3.basic.netcracker.backend.entity.Lesson;
import com.group3.basic.netcracker.backend.entity.LessonMissing;
import com.group3.basic.netcracker.backend.entity.User;
import com.group3.basic.netcracker.backend.service.CheckAttendanceService;
import com.group3.basic.netcracker.backend.util.dtomapper.LessonAttendanceDtoMapper;
import com.group3.basic.netcracker.backend.util.dtomapper.TrainerAttendanceDtoMapper;
import com.group3.basic.netcracker.backend.util.dtomapper.UserAttendanceDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckAttendanceServiceImpl implements CheckAttendanceService {

    private final LessonDao lessonDao;
    private final UserDao userDao;
    private final CourseDAO courseDao;
    private final LessonMissingDao lessonMissingDao;
    private final TrainerAttendanceDtoMapper trainerAttendanceDtoMapper;
    private final UserAttendanceDtoMapper userAttendanceDtoMapper;
    private final LessonAttendanceDtoMapper lessonAttendanceDtoMapper;

    @Autowired
    public CheckAttendanceServiceImpl(LessonDao lessonDao, UserDao userDao, CourseDAO courseDao, LessonMissingDao lessonMissingDao, TrainerAttendanceDtoMapper trainerAttendanceDtoMapper, UserAttendanceDtoMapper userAttendanceDtoMapper, LessonAttendanceDtoMapper lessonAttendanceDtoMapper) {
        this.lessonDao = lessonDao;
        this.userDao = userDao;
        this.courseDao = courseDao;
        this.lessonMissingDao = lessonMissingDao;
        this.trainerAttendanceDtoMapper = trainerAttendanceDtoMapper;
        this.userAttendanceDtoMapper = userAttendanceDtoMapper;
        this.lessonAttendanceDtoMapper = lessonAttendanceDtoMapper;
    }


    @Override
    public List<LessonAttendanceDto> getTodayLessonsByTrainer (int trainerId) {

        List<LessonAttendanceDto> lessonAttendanceDtoList = new ArrayList<>();
        for (Lesson l : lessonDao.getTodayLessonsByTrainer(trainerId)) {
            LessonAttendanceDto lad = lessonAttendanceDtoMapper.toLessonAttendanceDto(l);
            lad.setCourseName(courseDao.getCourseByLesson(l.getLessonId()).getName());
            lessonAttendanceDtoList.add(lad);
        }

        return lessonAttendanceDtoList;
    }

    @Override
    public CheckLessonAttendanceDto getFullCheckAttendance(int lessonId) {

        Course course = courseDao.getCourseByLesson(lessonId);

        CheckLessonAttendanceDto checkLessonAttendanceDto = new CheckLessonAttendanceDto();
        checkLessonAttendanceDto.setLessonId(lessonId);
        checkLessonAttendanceDto.setCourseName(course.getName());
        checkLessonAttendanceDto.setLessonDate(lessonDao.getLessonById(lessonId).getStartDateTime());
        checkLessonAttendanceDto.setCancel(false);
        checkLessonAttendanceDto.setTrainer(trainerAttendanceDtoMapper.toTrainerAttendanceDto(userDao.getTrainerByCourse(course.getId())));

        List<UserAttendanceDto> userAttendanceDtoList = new ArrayList<>();
        List<User> userList = userDao.getUsersByLesson(lessonId);
        for (User u : userList) {

            UserAttendanceDto uad = userAttendanceDtoMapper.toUserAttendanceDto(u);
            uad.setLessonId(lessonId);
            userAttendanceDtoList.add(uad);

        }
        checkLessonAttendanceDto.setUserAttendanceDtoList(userAttendanceDtoList);

        return checkLessonAttendanceDto;
    }

    @Override
    public void changeLessonMissing(int userId, int lessonId, String reason) {

        if (reason.equals("Present")) {
            for (LessonMissing lm : lessonMissingDao.getLessonMissingByLesson(lessonId)) {
                if (userId == lm.getUserId()) {
                    lessonMissingDao.delete(userId, lessonId);
                }
            }
        } else {
            if (isExist(userId, lessonId)) {
                lessonMissingDao.add(userId, lessonId, reason);
            } else {
                lessonMissingDao.updateReason(userId, lessonId, reason);
            }
        }

    }

    private boolean isExist (int userId, int lessonId) {

        for (LessonMissing lm : lessonMissingDao.getLessonMissingByLesson(lessonId)) {
            if (lm.getUserId() == userId) {
                return true;
            }
        }
        return false;
    }


}
