package com.group3.basic.netcracker.backend.service.impl;

import java.util.List;
import java.util.TreeMap;

import com.group3.basic.netcracker.backend.dao.ScheduleDao;
import com.group3.basic.netcracker.backend.service.ScheduleService;
import com.group3.basic.netcracker.backend.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	private final ScheduleDao scheduleDao;

	@Autowired
	public ScheduleServiceImpl(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

	@Override
	public void createSchedule(int userId, int[] timeSlotId, boolean isChoosen) {
		scheduleDao.createSchedule(userId, timeSlotId, isChoosen);
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
	public List listScheduleWithCourseAndTimeSlotAndUser(){
		return scheduleDao.listScheduleWithCourseAndTimeSlotAndUser();
	}

	@Override
	public void generateSchedule(int course){
		scheduleDao.generateSchedule(course);
	}

}
