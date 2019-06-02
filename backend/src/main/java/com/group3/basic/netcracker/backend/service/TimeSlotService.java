package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.entity.TimeSlot;

import java.time.LocalTime;
import java.util.List;

public interface TimeSlotService {

    void createTimeSlot(LocalTime startTime, LocalTime endTime, String weekDay, int courseId);

    boolean isTimeslotExists(LocalTime of, LocalTime of2, String weekDay, int courseId);

    TimeSlot getTimeSlotById(int id);

    List<TimeSlot> listTimeSlots();

    void removeTimeSlot(int id);

    void updateTimeSlot(LocalTime startTime, LocalTime endTime, String weekDay, int courseId, int id);

    List getTimeslotsOfCourse(String name);
}
