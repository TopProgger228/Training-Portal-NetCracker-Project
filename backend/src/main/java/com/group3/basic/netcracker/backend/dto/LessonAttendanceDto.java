package com.group3.basic.netcracker.backend.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class LessonAttendanceDto {

    int lessonId;
    boolean isCancel;
    Date startDateTime;


    public LessonAttendanceDto() {
    }

    public LessonAttendanceDto(int lessonId, boolean isCancel, Date startDateTime) {
        this.lessonId = lessonId;
        this.isCancel = isCancel;
        this.startDateTime = startDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonAttendanceDto that = (LessonAttendanceDto) o;
        return lessonId == that.lessonId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonId);
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

}
