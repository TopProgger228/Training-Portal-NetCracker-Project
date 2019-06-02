package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.entity.Schedule;

import java.util.List;


public interface ScheduleService {

    void createSchedule(int userId, Integer[] timeSlotId, boolean isChoosen);

    Integer[] isScheduleExists(int userId, Integer[] timeSlotId, boolean isChoosen);

    Schedule getScheduleById(int id);

    List<Schedule> Schedule();

    void removeSchedule(int id);

    List getScheduleOfStudent(String username);

    void updateSchedule(int userId, int timeSlotId, boolean isChoosen, int id);

    List listScheduleWithCourseAndTimeSlotAndUser();

    void generateLesson(int course);

    void generateSchedule(int course);
}
