package com.group3.basic.netcracker.backend.dao;

import com.group3.basic.netcracker.backend.entity.StudySchedule;

import java.util.List;
import java.util.TreeMap;


public interface StudyScheduleDao {
 
	StudySchedule getStudyScheduleById(int id);
    List<StudySchedule> listStudySchedule();
    void removeStudySchedule(int id);
	void createStudySchedule(int courseId, int userId, int timeSlotId, boolean isChoosen);
	void updateStudySchedule(int courseId, int userId, int timeSlotId, boolean isChoosen, int id);
	TreeMap<Integer, Integer> getChooseCount(int course);
}
