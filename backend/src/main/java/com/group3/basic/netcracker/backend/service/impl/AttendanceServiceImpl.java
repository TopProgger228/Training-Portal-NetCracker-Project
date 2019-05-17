package com.group3.basic.netcracker.backend.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import com.group3.basic.netcracker.backend.dao.*;
import com.group3.basic.netcracker.backend.dto.*;
import com.group3.basic.netcracker.backend.entity.*;
import com.group3.basic.netcracker.backend.util.dtomapper.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.group3.basic.netcracker.backend.service.AttendanceService;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceDao attendanceDao;
    private final CourseDAO courseDao;
    private final LessonDao lessonDao;
    private final UserDao userDao;
    private final LessonMissingDao lessonMissingDao;
    private final TimeSlotDao timeSlotDao;
    private final CourseAttendanceDtoMapper courseAttendanceDtoMapper;
    private final LessonAttendanceDtoMapper lessonAttendanceDtoMapper;
    private final UserAttendanceDtoMapper userAttendanceDtoMapper;
    private final TrainerAttendanceDtoMapper trainerAttendanceDtoMapper;
    private final TrainerSelectorDtoMapper trainerSelectorDtoMapper;
    private final StudentAttendanceForManagerDtoMapper studentAttendanceForManagerDtoMapper;

    @Autowired
    public AttendanceServiceImpl(AttendanceDao attendanceDao, CourseDAO courseDao, LessonDao lessonDao, UserDao userDao, LessonMissingDao lessonMissingDao, TimeSlotDao timeSlotDao, CourseAttendanceDtoMapper courseAttendanceDtoMapper, LessonAttendanceDtoMapper lessonAttendanceDtoMapper, UserAttendanceDtoMapper userAttendanceDtoMapper, TrainerAttendanceDtoMapper trainerAttendanceDtoMapper, TrainerSelectorDtoMapper trainerSelectorDtoMapper, StudentAttendanceForManagerDtoMapper studentAttendanceForManagerDtoMapper) {
        this.attendanceDao = attendanceDao;
        this.courseDao = courseDao;
        this.lessonDao = lessonDao;
        this.userDao = userDao;
        this.lessonMissingDao = lessonMissingDao;
        this.timeSlotDao = timeSlotDao;
        this.courseAttendanceDtoMapper = courseAttendanceDtoMapper;
        this.lessonAttendanceDtoMapper = lessonAttendanceDtoMapper;
        this.userAttendanceDtoMapper = userAttendanceDtoMapper;
        this.trainerAttendanceDtoMapper = trainerAttendanceDtoMapper;
        this.trainerSelectorDtoMapper = trainerSelectorDtoMapper;
        this.studentAttendanceForManagerDtoMapper = studentAttendanceForManagerDtoMapper;
    }


    @Override
    public List<Attendance> listAttendance() {
        return attendanceDao.listAttendance();
    }


    @Override
    public List<CourseAttendanceDto> getAllCourseAttendance() {

        List<Course> courseList = courseDao.listCourses();

        return getCourseAttendanceDtoList(courseList);
    }


    @Override
    public List<LessonAttendanceDto> getLessonsOfCourseAttendance(int courseId) {

        List<LessonAttendanceDto> lessonAttendanceDtoList = new ArrayList<>();
        List<Lesson> lessonsList = lessonDao.getLessonByCourse(courseId);

        for (Lesson l : lessonsList) {
            LessonAttendanceDto lad = lessonAttendanceDtoMapper.toLessonAttendanceDto(l);
            lad.setStartTime(timeSlotDao.getTimeSlotByLessonId(l.getLessonId()).getStartTime());
            lad.setEndTime(timeSlotDao.getTimeSlotByLessonId(l.getLessonId()).getEndTime());
            lessonAttendanceDtoList.add(lad);

        }

        return lessonAttendanceDtoList;

    }

    @Override
    public List<LessonAttendanceDto> getLessonsOfCourseAttendanceByUser(int courseId, int userId) {

        List<LessonAttendanceDto> lessonAttendanceDtoList = new ArrayList<>();
        List<Lesson> lessonsList = lessonDao.getLessonByCourse(courseId);
        Date date = new Date();

        for (Lesson l : lessonsList) {
            List<LessonMissing> lessonMissingList = lessonMissingDao.getLessonMissingByLesson(l.getLessonId());
            LessonAttendanceDto lad = lessonAttendanceDtoMapper.toLessonAttendanceDto(l);
            lad.setStartTime(timeSlotDao.getTimeSlotByLessonId(l.getLessonId()).getStartTime());
            lad.setEndTime(timeSlotDao.getTimeSlotByLessonId(l.getLessonId()).getEndTime());


            for (LessonMissing lm : lessonMissingList) {
                if (userId == lm.getUserId()) {
                    lad.setAttStatus(lm.getReason());
                }
            }
            if (lad.getAttStatus() == null & lad.getStartDateTime().compareTo(date) < 0 & l.isCancel() != true) {
                lad.setAttStatus("Present");
            }
            lessonAttendanceDtoList.add(lad);
        }
        return lessonAttendanceDtoList;
    }



    @Override
    public List<UserAttendanceDto> getUsersOfCourseAttendance(int lessonId) {

        List<UserAttendanceDto> userAttendanceDtoList = new ArrayList<>();
        List<User> userList = userDao.getUsersByLesson(lessonId);
        List<LessonMissing> lessonMissingList = lessonMissingDao.getLessonMissingByLesson(lessonId);

        for (User u : userList) {
            UserAttendanceDto uad = userAttendanceDtoMapper.toUserAttendanceDto(u);
            uad.setLessonId(lessonId);

            for (LessonMissing lm : lessonMissingList) {
                if (uad.getId() == lm.getUserId()) {
                    uad.setAttendanceStatus(lm.getReason());
                }
            }
            if (uad.getAttendanceStatus() == null) {
                uad.setAttendanceStatus("Present");
            }

            userAttendanceDtoList.add(uad);

        }

        return userAttendanceDtoList;
    }


    @Override
    public List<CourseAttendanceDto> getCourseAttendanceByUser(String username) {

        List<Course> courseList = courseDao.getCourseByUserUsername(username);

        return getCourseAttendanceDtoList(courseList);

    }

    @Override
    public List<CourseAttendanceDto> getCourseAttendanceByUserId(int userId) {

        List<Course> courseList = courseDao.getCourseByUserId(userId);
        List<CourseAttendanceDto> listDto = getCourseAttendanceDtoList(courseList);

        for (CourseAttendanceDto cad : listDto) {
            int missingLessonCount = lessonMissingDao.getMissingLessonCountByUserAndCourse(cad.getCourseId(), userId);
            cad.setPresentLessonCount(cad.getFinishedLessonCount() - missingLessonCount);
        }
        return listDto;

    }

    @Override
    public List<TrainerSelectorDto> getTrainerForSelector() {

        List<TrainerSelectorDto> trainerSelectorDtoList = new ArrayList<>();
        List<Trainer> trainerList = userDao.getTrainers();
        for (Trainer t : trainerList) {

            trainerSelectorDtoList.add(trainerSelectorDtoMapper.toTrainerSelectorDto(t));

        }

        return trainerSelectorDtoList;
    }

    @Override
    public List<CourseAttendanceDto> getCourseAttendanceByTrainer(int id) {

        List<Course> courseList = courseDao.getCourseByTrainerId(id);

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
        String[] reasonList = {
                "Absent due to business trip",
                "Absent without reason",
                "Absent due to sick leave",
                "Absent due to project activities"};

        for (User u : userList) {
            StudentAttendanceForManagerDto tmp = studentAttendanceForManagerDtoMapper.toStudentAttendanceForManagerDto(u);
            tmp.setTotalLessonCount(lessonDao.getLessonCountTillTodayByStudent(u.getId()));

            Map<String, Integer> map = new HashMap<>();
            int presentLessonCount = tmp.getTotalLessonCount();
            List<LessonMissing> lessonMissingList = lessonMissingDao.getLessonMissingByUser(u.getId());
            if ( lessonMissingList != null && lessonMissingList.size() > 0 ) {
                int count;
                for (int i = 0; i < reasonList.length; i++) {
                    count = 0;
                    for (int j = 0; j < lessonMissingList.size(); j++) {
                        if (reasonList[i].equals(lessonMissingList.get(j).getReason())) {
                            count++;
                        }
                    }
                    map.put(reasonList[i], count);
                    presentLessonCount -= count;
                }
            } else {
                map.put("Absent due to business trip", 0);
                map.put("Absent without reason", 0);
                map.put("Absent due to sick leave", 0);
                map.put("Absent due to project activities", 0);
            }
            map.put("Present", presentLessonCount);
            tmp.setLessonsMap(map);
            dtoList.add(tmp);
        }

        return dtoList;
    }

    private List<CourseAttendanceDto> getCourseAttendanceDtoList(List<Course> courseList) {

        List<CourseAttendanceDto> courseAttendanceDtoList = new ArrayList<>();

        for (Course c : courseList) {
            CourseAttendanceDto cad = courseAttendanceDtoMapper.toCourseAttendanceDto(c);
            cad.setTrainer(trainerAttendanceDtoMapper.toTrainerAttendanceDto(userDao.getTrainerByCourse(c.getId())));
            cad.setTotalLessonCount(lessonDao.getLessonCountInCourse(c.getId()));
            cad.setFinishedLessonCount(lessonDao.getLessonCountInCourseTillToday(c.getId()));

            courseAttendanceDtoList.add(cad);
        }

        return courseAttendanceDtoList;

    }


}
