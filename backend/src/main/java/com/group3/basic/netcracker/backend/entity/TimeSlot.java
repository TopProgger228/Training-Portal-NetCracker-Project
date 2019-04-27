package com.group3.basic.netcracker.backend.entity;

import java.sql.Time;

public class TimeSlot {

	int id;
	Time startTime;
	Time endTime;
	String weekDay;
	int courseId;

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


	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time time) {
		this.startTime = time;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
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
