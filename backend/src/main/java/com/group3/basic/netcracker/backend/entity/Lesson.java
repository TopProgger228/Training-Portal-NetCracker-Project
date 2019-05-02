package com.group3.basic.netcracker.backend.entity;

import java.util.Date;
import java.util.Objects;

public class Lesson {

    int lessonId;
    int courseId;
    boolean isCancel;
    Date startDateTime;

    public Lesson() {
    }

    public Lesson(int lessonId, int groupId, boolean isCancel, Date startDateTime) {
        this.lessonId = lessonId;
        this.courseId = groupId;
        this.isCancel = isCancel;
        this.startDateTime = startDateTime;
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

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
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
