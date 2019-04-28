package com.group3.basic.netcracker.backend.entity;

import java.sql.Time;
import java.time.LocalTime;

public class TimeSlot {

	int id;
	LocalTime startTime;
	LocalTime endTime;
	String weekDay;
	int courseId;

	public TimeSlot(int id, LocalTime startTime, LocalTime endTime, String weekDay, int courseId) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.weekDay = weekDay;
		this.courseId = courseId;
	}

	public TimeSlot() {
	}

	// getter and setter methods
	public int getId() {
		return id;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	@Override
	public String toString() {
		String value = weekDay + ' ' + startTime + ' ' + ' ' + endTime;
		return value;
	}

}
