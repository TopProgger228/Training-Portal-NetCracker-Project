package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.entity.TimeSlot;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.List;

@Service
public interface TimeSlotService{

void createTimeSlot(LocalTime startTime, LocalTime endTime, String weekDay, int courseId);

TimeSlot getTimeSlotById(int id);

List<TimeSlot> TimeSlots();

void removeTimeSlot(int id);

void updateTimeSlot(LocalTime startTime, LocalTime endTime, String weekDay, int courseId, int id);
}