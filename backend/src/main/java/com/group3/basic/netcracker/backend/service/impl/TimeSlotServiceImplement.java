package com.group3.basic.netcracker.backend.service.impl;

import java.time.LocalTime;
import java.util.List;

import com.group3.basic.netcracker.backend.dao.TimeSlotDao;
import com.group3.basic.netcracker.backend.service.TimeSlotServiceInterface;
import com.group3.basic.netcracker.backend.entity.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;

public class TimeSlotServiceImplement implements TimeSlotServiceInterface {
	private final TimeSlotDao timeSlotDao;

	@Autowired
	public TimeSlotServiceImplement(TimeSlotDao TimeSlotDao) {
		this.timeSlotDao = TimeSlotDao;
	}

	@Override
	public void createTimeSlot(LocalTime startTime, LocalTime endTime, String weekDay) {
		timeSlotDao.createTimeSlot(startTime, endTime, weekDay);
	}

	@Override
	public TimeSlot getTimeSlotById(int id) {
		return timeSlotDao.getTimeSlotById(id);
	}

	@Override
	public List<TimeSlot> TimeSlots() {
	return timeSlotDao.listTimeSlots();
	}

	@Override
	public void removeTimeSlot(int id) {
	timeSlotDao.removeTimeSlot(id);
	}

	@Override
	public void updateTimeSlot(LocalTime startTime, LocalTime endTime, String weekDay, int id) {
     timeSlotDao.updateTimeSlot(startTime, endTime, weekDay, id);
	}

}
