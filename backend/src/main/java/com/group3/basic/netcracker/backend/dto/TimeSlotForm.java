package com.group3.basic.netcracker.backend.dto;

import javax.validation.constraints.NotBlank;

public class TimeSlotForm {

    private String startTime;

    private String endTime;

    private String weekDay;

    @NotBlank
    private int course_id;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeek_day(String weekDay) {
        this.weekDay = weekDay;
    }
}
