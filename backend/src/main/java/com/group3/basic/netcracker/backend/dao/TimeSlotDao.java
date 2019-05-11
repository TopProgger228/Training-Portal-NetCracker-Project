package com.group3.basic.netcracker.backend.dao;

import com.group3.basic.netcracker.backend.entity.TimeSlot;

import java.time.LocalTime;
import java.util.List;

public interface TimeSlotDao {

    TimeSlot getTimeSlotById(int id);

    List<TimeSlot> listTimeSlots();

    void removeTimeSlot(int id);

    void createTimeSlot(LocalTime of, LocalTime of2, String weekDay, int courseId);

    void updateTimeSlot(LocalTime localTime, LocalTime localTime2, String weekDay, int courseId, int id);

    TimeSlot getTimeSlotByLessonId(int id);

    List getTimeslotsOfCourse(String name);
}