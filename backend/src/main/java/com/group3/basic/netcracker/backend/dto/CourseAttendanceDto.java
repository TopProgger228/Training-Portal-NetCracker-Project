package com.group3.basic.netcracker.backend.dto;

import java.util.Objects;

public class CourseAttendanceDto {

    private int courseId;
    private String name;
    private String skillLevel;
    private TrainerAttendanceDto trainer;
    private int totalLessonCount;
    private int presentLessonCount;
    private int finishedLessonCount;

    public CourseAttendanceDto() {
    }

    public CourseAttendanceDto(int courseId, String name, String skillLevel, TrainerAttendanceDto trainer, int totalLessonCount, int presentLessonCount, int finishedLessonCount) {
        this.courseId = courseId;
        this.name = name;
        this.skillLevel = skillLevel;
        this.trainer = trainer;
        this.totalLessonCount = totalLessonCount;
        this.presentLessonCount = presentLessonCount;
        this.finishedLessonCount = finishedLessonCount;
    }

    public int getFinishedLessonCount() {
        return finishedLessonCount;
    }

    public void setFinishedLessonCount(int finishedLessonCount) {
        this.finishedLessonCount = finishedLessonCount;
    }

    public int getTotalLessonCount() {
        return totalLessonCount;
    }

    public void setTotalLessonCount(int totalLessonCount) {
        this.totalLessonCount = totalLessonCount;
    }

    public int getPresentLessonCount() {
        return presentLessonCount;
    }

    public void setPresentLessonCount(int presentLessonCount) {
        this.presentLessonCount = presentLessonCount;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public TrainerAttendanceDto getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerAttendanceDto trainer) {
        this.trainer = trainer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseAttendanceDto that = (CourseAttendanceDto) o;
        return courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
}
