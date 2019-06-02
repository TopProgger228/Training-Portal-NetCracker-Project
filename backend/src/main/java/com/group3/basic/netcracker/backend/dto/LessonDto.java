package com.group3.basic.netcracker.backend.dto;

import java.time.LocalTime;
import java.util.Date;

public class LessonDto {

    private int lessonId;
    private boolean isCancel;
    private Date lessonDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public LessonDto() {
    }

    public LessonDto(int lessonId, boolean isCancel, Date startDateTime, LocalTime startTime, LocalTime endTime) {
        this.lessonId = lessonId;
        this.isCancel = isCancel;
        this.lessonDate = startDateTime;
        this.startTime = startTime;
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

    public Date getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Date startDateTime) {
        this.lessonDate = startDateTime;
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
}
