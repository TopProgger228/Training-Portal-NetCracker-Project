package com.group3.basic.netcracker.backend.dto;

import java.util.Objects;

public class UserAttendanceDto {

    int id;
    String userFirstName;
    String userLastName;
    int lessonId;
    String AttendanceStatus;

    public UserAttendanceDto() {
    }

    public UserAttendanceDto(int id, String userFirstName, String userLastName, int lessonId, String attendanceStatus) {
        this.id = id;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.lessonId = lessonId;
        AttendanceStatus = attendanceStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getAttendanceStatus() {
        return AttendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        AttendanceStatus = attendanceStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAttendanceDto that = (UserAttendanceDto) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
