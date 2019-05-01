package com.group3.basic.netcracker.backend.dto;

import java.util.Date;
import java.util.List;

public class CheckLessonAttendanceDto {

    int lessonId;
    String courseName;
    Date lessonDate;
    boolean isCancel;
    TrainerAttendanceDto trainer;
    List<UserAttendanceDto> userAttendanceDtoList;

    public CheckLessonAttendanceDto() {
    }

    public CheckLessonAttendanceDto(int lessonId, String courseName, Date lessonDate, boolean isCancel, TrainerAttendanceDto trainer, List<UserAttendanceDto> userAttendanceDtoList) {
        this.lessonId = lessonId;
        this.courseName = courseName;
        this.lessonDate = lessonDate;
        this.isCancel = isCancel;
        this.trainer = trainer;
        this.userAttendanceDtoList = userAttendanceDtoList;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Date lessonDate) {
        this.lessonDate = lessonDate;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean cancel) {
        isCancel = cancel;
    }

    public TrainerAttendanceDto getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerAttendanceDto trainer) {
        this.trainer = trainer;
    }

    public List<UserAttendanceDto> getUserAttendanceDtoList() {
        return userAttendanceDtoList;
    }

    public void setUserAttendanceDtoList(List<UserAttendanceDto> userAttendanceDtoList) {
        this.userAttendanceDtoList = userAttendanceDtoList;
    }


}
