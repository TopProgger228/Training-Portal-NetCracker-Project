package com.group3.basic.netcracker.backend.entity;

import java.util.Date;
import java.util.Objects;

public class Lesson {

    private int lessonId;
    private int courseId;
    private boolean isCancel;
    private Date lessonDate;
    private int timeSlotId;

    public Lesson() {
    }

    public Lesson(int lessonId, int groupId, boolean isCancel, Date lessonDate, int timeSlotId) {
        this.lessonId = lessonId;
        this.courseId = groupId;
        this.isCancel = isCancel;
        this.lessonDate = lessonDate;
        this.timeSlotId = timeSlotId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public int getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(int timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return lessonId == lesson.lessonId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonId);
    }

}
