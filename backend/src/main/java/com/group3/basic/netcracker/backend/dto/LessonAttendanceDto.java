package com.group3.basic.netcracker.backend.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class LessonAttendanceDto {

    private int lessonId;
    private boolean isCancel;
    private Date startDateTime;
    private String courseName;

    public LessonAttendanceDto(int lessonId, boolean isCancel, Date startDateTime, String courseName) {
        this.lessonId = lessonId;
        this.isCancel = isCancel;
        this.startDateTime = startDateTime;
        this.courseName = courseName;
    }

    public LessonAttendanceDto() {
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
