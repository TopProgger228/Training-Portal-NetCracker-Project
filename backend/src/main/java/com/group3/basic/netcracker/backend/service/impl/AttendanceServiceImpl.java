package com.group3.basic.netcracker.backend.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import com.group3.basic.netcracker.backend.dao.*;
import com.group3.basic.netcracker.backend.dto.*;
import com.group3.basic.netcracker.backend.entity.*;
import com.group3.basic.netcracker.backend.util.dtomapper.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.group3.basic.netcracker.backend.service.AttendanceService;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final CourseDao courseDao;
    private final LessonDao lessonDao;
    private final UserDao userDao;
    private final LessonMissingDao lessonMissingDao;
    private final CourseAttendanceDtoMapper courseAttendanceDtoMapper;
    private final LessonAttendanceDtoMapper lessonAttendanceDtoMapper;
    private final UserAttendanceDtoMapper userAttendanceDtoMapper;
    private final StudentAttendanceForManagerDtoMapper studentAttendanceForManagerDtoMapper;

    @Autowired
    public AttendanceServiceImpl(CourseDao courseDao, LessonDao lessonDao, UserDao userDao,
                                 LessonMissingDao lessonMissingDao, CourseAttendanceDtoMapper courseAttendanceDtoMapper,
                                 LessonAttendanceDtoMapper lessonAttendanceDtoMapper,
                                 UserAttendanceDtoMapper userAttendanceDtoMapper,
                                 StudentAttendanceForManagerDtoMapper studentAttendanceForManagerDtoMapper) {
        this.courseDao = courseDao;
        this.lessonDao = lessonDao;
        this.userDao = userDao;
        this.lessonMissingDao = lessonMissingDao;
        this.courseAttendanceDtoMapper = courseAttendanceDtoMapper;
        this.lessonAttendanceDtoMapper = lessonAttendanceDtoMapper;
        this.userAttendanceDtoMapper = userAttendanceDtoMapper;
        this.studentAttendanceForManagerDtoMapper = studentAttendanceForManagerDtoMapper;
    }


    @Override
    public List<CourseAttendanceDto> getAllCourseAttendance() {
        List<Course> courseList = courseDao.listCourses();

        return getCourseAttendanceDtoList(courseList);
    }

    @Override
    public List<LessonAttendanceDto> getLessonsOfCourseAttendance(int courseId) {
        List<Lesson> lessonsList = lessonDao.getLessonByCourse(courseId);

        return lessonsList.stream().map(lessonAttendanceDtoMapper::toLessonAttendanceDto).collect(Collectors.toList());
    }

    @Override
    public List<LessonAttendanceDto> getLessonsOfCourseAttendanceByUser(int courseId, int userId) {
        List<LessonAttendanceDto> lessonAttendanceDtoList = new ArrayList<>();
        List<Lesson> lessonsList = lessonDao.getLessonByCourse(courseId);
        Date date = new Date();
        for (Lesson lesson : lessonsList) {
            List<LessonMissing> lessonMissingList = lessonMissingDao.getLessonMissingByLesson(lesson.getLessonId());
            LessonAttendanceDto lessonAttendanceDto = lessonAttendanceDtoMapper.toLessonAttendanceDto(lesson);
            for (LessonMissing lessonMissing : lessonMissingList) {
                if (userId == lessonMissing.getUserId()) {
                    lessonAttendanceDto.setAttStatus(lessonMissing.getReason());
                }
            }
            if (lessonAttendanceDto.getAttStatus() == null && lessonAttendanceDto.getLessonDate().compareTo(date) < 0 && !lesson.isCancel()) {
                lessonAttendanceDto.setAttStatus(AttendanceStatus.PRESENT.toString());
            }
            lessonAttendanceDtoList.add(lessonAttendanceDto);
        }

        return lessonAttendanceDtoList;
    }


    public List<UserAttendanceDto> getUsersOfCourseAttendance(int lessonId) {

        List<UserAttendanceDto> userAttendanceDtoList = new ArrayList<>();
        List<User> userList = userDao.getUsersByLesson(lessonId);
        List<LessonMissing> lessonMissingList = lessonMissingDao.getLessonMissingByLesson(lessonId);
        for (User user : userList) {
            UserAttendanceDto userAttendanceDto = userAttendanceDtoMapper.toUserAttendanceDto(user);
            for (LessonMissing lessonMissing : lessonMissingList) {
                if (userAttendanceDto.getId() == lessonMissing.getUserId()) {
                    userAttendanceDto.setAttendanceStatus(lessonMissing.getReason());
                }
            }
            if (userAttendanceDto.getAttendanceStatus() == null) {
                userAttendanceDto.setAttendanceStatus(AttendanceStatus.PRESENT.toString());
            }
            userAttendanceDtoList.add(userAttendanceDto);
        }

        return userAttendanceDtoList;
    }


    @Override
    public List<CourseAttendanceDto> getCourseAttendanceByUser(final String username) {
        List<Course> courseList = courseDao.getCourseByUserUsername(username);

        return getCourseAttendanceDtoList(courseList);
    }

    @Override
    public List<CourseAttendanceDto> getCourseAttendanceByUserId(int userId) {

        List<Course> courseList = courseDao.getCourseByUserId(userId);
        List<CourseAttendanceDto> listDto = getCourseAttendanceDtoList(courseList);

        for (CourseAttendanceDto courseAttendanceDto : listDto) {
            int missingLessonCount = lessonMissingDao.getMissingLessonCountByUserAndCourse(courseAttendanceDto.getCourseId(), userId);
            courseAttendanceDto.setPresentLessonCount(lessonDao
                    .getLessonCountInCourseTillToday(courseAttendanceDto.getCourseId()) - missingLessonCount);
        }
        return listDto;

    }

    @Override
    public List<CourseAttendanceDto> getCourseAttendanceByTrainerUsername(String username) {
        List<Course> courseList = courseDao.getCourseByTrainerUsername(username);

        return getCourseAttendanceDtoList(courseList);
    }


    @Override
    public List<CourseAttendanceDto> getCourseAttendanceBySkillLevel(String level) {
        List<Course> courseList = courseDao.getCourseBySkillLevel(level);

        return getCourseAttendanceDtoList(courseList);

    }

    @Override
    public List<StudentAttendanceForManagerDto> getStudentAttendanceForManagerDto(String userName) {

        List<StudentAttendanceForManagerDto> dtoList = new ArrayList<>();
        List<User> userList = userDao.getStudentsOfManager(userName);
        for (User user : userList) {
            StudentAttendanceForManagerDto studentAttendanceForManagerDto = studentAttendanceForManagerDtoMapper.toStudentAttendanceForManagerDto(user);
            studentAttendanceForManagerDto.setTotalLessonCount(lessonDao.getLessonCountTillTodayByStudent(user.getId()));

            Map<String, Integer> AttendanceStatusMap = new HashMap<>();
            AttendanceStatusMap.put(AttendanceStatus.ABSENT_DUE_TO_BUSINESS_TRIP.toString(), 0);
            AttendanceStatusMap.put(AttendanceStatus.ABSENT_WITHOUT_REASON.toString(), 0);
            AttendanceStatusMap.put(AttendanceStatus.ABSENT_DUE_TO_SICK_LEAVE.toString(), 0);
            AttendanceStatusMap.put(AttendanceStatus.ABSENT_DUE_TO_PROJECT_ACTIVITIES.toString(), 0);
            int presentLessonCount = studentAttendanceForManagerDto.getTotalLessonCount();
            List<LessonMissing> lessonMissingList = lessonMissingDao.getLessonMissingByUser(user.getId());
            if (lessonMissingList != null && lessonMissingList.size() > 0) {
                int count;
                for ( Map.Entry<String, Integer> oneStatus : AttendanceStatusMap.entrySet()) {
                    count = 0;
                    for (LessonMissing aLessonMissingList : lessonMissingList) {
                        if (oneStatus.getKey().equals(aLessonMissingList.getReason())) {
                            count++;
                        }
                    }
                    AttendanceStatusMap.put(oneStatus.getKey(), count);
                    presentLessonCount -= count;
                }
            }
            AttendanceStatusMap.put(AttendanceStatus.PRESENT.toString(), presentLessonCount);
            studentAttendanceForManagerDto.setLessonsMap(AttendanceStatusMap);
            dtoList.add(studentAttendanceForManagerDto);
        }

        return dtoList;
    }

    private List<CourseAttendanceDto> getCourseAttendanceDtoList(List<Course> courseList) {
        return courseList.stream().map(courseAttendanceDtoMapper::toCourseAttendanceDto).collect(Collectors.toList());
    }
}