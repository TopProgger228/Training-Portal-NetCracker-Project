package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.entity.Schedule;

import java.util.List;
import java.util.TreeMap;


public interface ScheduleService {
	
	void createSchedule(int courseId, int userId, int timeSlotId, boolean isChoosen);
	Schedule getScheduleById(int id);
	List<Schedule> Schedule();
	void removeSchedule(int id);
	void updateSchedule(int courseId, int userId, int timeSlotId, boolean isChoosen, int id);
	TreeMap<Integer, Integer> getChooseCount(int course);
}
