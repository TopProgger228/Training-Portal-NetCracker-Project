package com.group3.basic.netcracker.backend.dto;

import java.time.LocalTime;
import java.util.Date;

public class LessonAttendanceDto {

    private int lessonId;
    private boolean isCancel;
    private Date lessonDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String courseName;
    private String attStatusForOneStudent;

    public LessonAttendanceDto(int lessonId, boolean isCancel, Date lessonDate, LocalTime startTime, LocalTime endTime, String courseName, String attStatusForOneStudent) {
        this.lessonId = lessonId;
        this.isCancel = isCancel;
        this.lessonDate = lessonDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.courseName = courseName;
        this.attStatusForOneStudent = attStatusForOneStudent;
    }

    public LessonAttendanceDto() {
    }

    public String getAttStatus() {
        return attStatusForOneStudent;
    }

    public void setAttStatus(String attStatusForOneStudent) {
        this.attStatusForOneStudent = attStatusForOneStudent;
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

    public Date getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Date lessonDate) {
        this.lessonDate = lessonDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
