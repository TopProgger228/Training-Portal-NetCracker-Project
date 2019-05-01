package com.group3.basic.netcracker.backend.entity;

public class LessonMissing {

    int id;
    int lessonId;
    int userId;
    String reason;

    public LessonMissing() {
    }

    public LessonMissing(int id, int lessonId, int userId, String reason) {
        this.id = id;
        this.lessonId = lessonId;
        this.userId = userId;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "LessonMissingDao{" +
                "id=" + id +
                ", lessonId=" + lessonId +
                ", userId=" + userId +
                ", reason='" + reason + '\'' +
                '}';
    }
}
