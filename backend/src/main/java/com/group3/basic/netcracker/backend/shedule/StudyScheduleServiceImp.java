package com.group3.basic.netcracker.backend.shedule;

import java.time.LocalTime;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.group3.basic.netcracker.backend.timeslot.TimeSlot;
import com.group3.basic.netcracker.backend.timeslot.TimeSlotDao;

public class StudyScheduleServiceImp implements StudyScheduleService {
	private final StudyScheduleDao studyScheduleDao;

	@Autowired
	public StudyScheduleServiceImp(StudyScheduleDao studyScheduleDao) {
		this.studyScheduleDao = studyScheduleDao;
	}

	@Override
	public void createStudySchedule(int courseId, int userId, int timeSlotId, boolean isChoosen) {
		studyScheduleDao.createStudySchedule(courseId, userId, timeSlotId, isChoosen);
	}

	@Override
	public StudySchedule getStudyScheduleById(int id) {
		return studyScheduleDao.getStudyScheduleById(id);
	}

	@Override
	public List<StudySchedule> StudySchedule() {
	return studyScheduleDao.listStudySchedule();
	}

	@Override
	public void removeStudySchedule(int id) {
		studyScheduleDao.removeStudySchedule(id);
	}

	@Override
	public void updateStudySchedule(int courseId, int userId, int timeSlotId, boolean isChoosen, int id) {
		studyScheduleDao.updateStudySchedule(courseId, userId, timeSlotId, isChoosen, id);
	}

	@Override
	public TreeMap<Integer, Integer> getChooseCount(int courseId) {
    TreeMap<Integer, Integer> tm  = studyScheduleDao.getChooseCount(courseId);
	return tm;
	}

}
