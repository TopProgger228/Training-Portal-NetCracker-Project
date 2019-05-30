package com.group3.basic.netcracker.backend.service.impl;

import java.util.List;
import java.util.TreeMap;

import com.group3.basic.netcracker.backend.dao.ScheduleDao;
import com.group3.basic.netcracker.backend.dao.TimeSlotDao;
import com.group3.basic.netcracker.backend.service.ScheduleService;
import com.group3.basic.netcracker.backend.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleDao scheduleDao;
    private final TimeSlotDao timeSlotDao;

    @Autowired
    public ScheduleServiceImpl(ScheduleDao scheduleDao, TimeSlotDao timeSlotDao) {
        this.scheduleDao = scheduleDao;
        this.timeSlotDao = timeSlotDao;
    }

    @Override
    public void createSchedule(int userId, Integer[] timeSlotId, boolean isChoosen) {
        scheduleDao.createSchedule(userId, timeSlotId, isChoosen);
        int courseId = timeSlotDao.getCourseIdById(timeSlotId[0]);
        if(!scheduleDao.isGroupExist(courseId, userId)){
            scheduleDao.connectUserAndCourse(courseId, userId);
        }
    }

    @Override
    public Integer[] isScheduleExists(int userId, Integer[] timeSlotId, boolean isChoosen) {
        return scheduleDao.isScheduleExists(userId, timeSlotId, isChoosen);
    }

    @Override
    public List getScheduleOfStudent(String username){
        return scheduleDao.getScheduleOfStudent(username);
    }

    @Override
    public Schedule getScheduleById(int id) {
        return scheduleDao.getScheduleById(id);
    }

    @Override
    public List<Schedule> Schedule() {
        return scheduleDao.listSchedule();
    }

    @Override
    public void removeSchedule(int id) {
        scheduleDao.removeSchedule(id);
    }

    @Override
    public void updateSchedule(int userId, int timeSlotId, boolean isChoosen, int id) {
        scheduleDao.updateSchedule(userId, timeSlotId, isChoosen, id);
    }

    @Override
    public List listScheduleWithCourseAndTimeSlotAndUser() {
        return scheduleDao.listScheduleWithCourseAndTimeSlotAndUser();
    }

    @Override
    public void generateSchedule(int course) {
        scheduleDao.generateSchedule(course);
    }

}
