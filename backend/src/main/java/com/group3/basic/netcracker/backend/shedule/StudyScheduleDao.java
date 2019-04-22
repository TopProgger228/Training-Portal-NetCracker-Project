package com.group3.basic.netcracker.backend.shedule;

import java.util.List;
import com.group3.basic.netcracker.backend.course.Course;

public interface StudyScheduleDao {
 
	StudySchedule getStudyScheduleById(int id);
    List<StudySchedule> listStudySchedule();
    void removeStudySchedule(int id);
	void createStudySchedule(int courseId, int userId, int timeSlotId, boolean isChoosen);
	void updateStudySchedule(int courseId, int userId, int timeSlotId, boolean isChoosen);
}
