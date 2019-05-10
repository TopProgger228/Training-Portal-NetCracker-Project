package com.group3.basic.netcracker.backend.dto;

import java.time.LocalTime;
import java.util.Date;

public class LessonAttendanceDto {

    private int lessonId;
    private boolean isCancel;
    private Date startDateTime;
    private LocalTime startTime;
    private LocalTime endTime;
    private String courseName;

    public LessonAttendanceDto(int lessonId, boolean isCancel, Date startDateTime, LocalTime startTime, LocalTime endTime, String courseName) {
        this.lessonId = lessonId;
        this.isCancel = isCancel;
        this.startDateTime = startDateTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseName = courseName;
    }

    public LessonAttendanceDto() {
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

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
