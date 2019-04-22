package com.group3.basic.netcracker.backend.timeslot;
import com.group3.basic.netcracker.backend.timeslot.TimeSlot;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

public interface TimeSlotDao {

	    TimeSlot getTimeSlotById(int id);
	    List<TimeSlot> listTimeSlots();
	    void removeTimeSlot(int id);
		void createTimeSlot(LocalTime of, LocalTime of2, String weekDay);
		void updateTimeSlot(LocalTime localTime, LocalTime localTime2, String weekDay, int id);
	}