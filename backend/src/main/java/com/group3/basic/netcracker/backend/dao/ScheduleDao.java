package com.group3.basic.netcracker.backend.dao;

import com.group3.basic.netcracker.backend.entity.Schedule;

import java.util.List;
import java.util.TreeMap;


public interface ScheduleDao {
 
	Schedule getScheduleById(int id);
    List<Schedule> listSchedule();
    void removeSchedule(int id);
	void createSchedule(int userId, Integer[] timeSlotId, boolean isChoosen);
	Integer[] isScheduleExists(int userId, Integer[] timeSlotId, boolean isChoosen);
	void updateSchedule(int userId, int timeSlotId, boolean isChoosen, int id);
	List listScheduleWithCourseAndTimeSlotAndUser();
	void generateSchedule(int course);
}
