package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.entity.StudySchedule;

import java.util.List;
import java.util.TreeMap;


public interface StudyScheduleService {
	
	void createStudySchedule(int courseId, int userId, int timeSlotId, boolean isChoosen);
	StudySchedule getStudyScheduleById(int id);
	List<StudySchedule> StudySchedule();
	void removeStudySchedule(int id);
	void updateStudySchedule(int courseId, int userId, int timeSlotId, boolean isChoosen, int id);
	TreeMap<Integer, Integer> getChooseCount(int course);
}
