package com.group3.basic.netcracker.backend.service.impl;

import java.time.LocalTime;
import java.util.List;

import com.group3.basic.netcracker.backend.dao.TimeSlotDao;
import com.group3.basic.netcracker.backend.service.TimeSlotService;
import com.group3.basic.netcracker.backend.entity.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {
    private final TimeSlotDao timeSlotDao;

    @Autowired
    public TimeSlotServiceImpl(TimeSlotDao TimeSlotDao) {
        this.timeSlotDao = TimeSlotDao;
    }

    @Override
    public void createTimeSlot(LocalTime startTime, LocalTime endTime, String weekDay, int courseId) {
        timeSlotDao.createTimeSlot(startTime, endTime, weekDay, courseId);
    }

    @Override
    public boolean isTimeslotExists(LocalTime of, LocalTime of2, String weekDay, int courseId) {
        return timeSlotDao.isTimeslotExists(of, of2, weekDay, courseId);
    }

    @Override
    public TimeSlot getTimeSlotById(int id) {
        return timeSlotDao.getTimeSlotById(id);
    }

    @Override
    public List<TimeSlot> listTimeSlots() {
        return timeSlotDao.listTimeSlots();
    }

    @Override
    public void removeTimeSlot(int id) {
        timeSlotDao.removeTimeSlot(id);
    }

    @Override
    public void updateTimeSlot(LocalTime startTime, LocalTime endTime, String weekDay, int courseId, int id) {
        timeSlotDao.updateTimeSlot(startTime, endTime, weekDay, courseId, id);
    }

    @Override
    public List getTimeslotsOfCourse(String name) {
        return timeSlotDao.getTimeslotsOfCourse(name);
    }

}
