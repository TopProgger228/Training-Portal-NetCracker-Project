package com.group3.basic.netcracker.backend.service.impl;

import java.util.List;
import java.util.TreeMap;

import com.group3.basic.netcracker.backend.dao.ScheduleDao;
import com.group3.basic.netcracker.backend.service.ScheduleService;
import com.group3.basic.netcracker.backend.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;

public class ScheduleServiceImp implements ScheduleService {
	private final ScheduleDao scheduleDao;

	@Autowired
	public ScheduleServiceImp(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

	@Override
	public void createSchedule(int courseId, int userId, int timeSlotId, boolean isChoosen) {
		scheduleDao.createSchedule(courseId, userId, timeSlotId, isChoosen);
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
	public void updateSchedule(int courseId, int userId, int timeSlotId, boolean isChoosen, int id) {
		scheduleDao.updateSchedule(courseId, userId, timeSlotId, isChoosen, id);
	}

	@Override
	public TreeMap<Integer, Integer> getChooseCount(int courseId) {
    TreeMap<Integer, Integer> tm  = scheduleDao.getChooseCount(courseId);
	return tm;
	}

}
