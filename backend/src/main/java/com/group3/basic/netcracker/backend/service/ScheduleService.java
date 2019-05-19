package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.entity.Schedule;

import java.util.List;
import java.util.TreeMap;


public interface ScheduleService {
	
	void createSchedule(int userId, int[] timeSlotId, boolean isChoosen);
	Schedule getScheduleById(int id);
	List<Schedule> Schedule();
	void removeSchedule(int id);
	void updateSchedule(int userId, int timeSlotId, boolean isChoosen, int id);
	List listScheduleWithCourseAndTimeSlotAndUser();
	void generateSchedule(int course);
}
