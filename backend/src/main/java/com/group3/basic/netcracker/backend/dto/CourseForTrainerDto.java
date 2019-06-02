package com.group3.basic.netcracker.backend.dto;

public class CourseForTrainerDto {
        private int courseId;
        private String name;
        private String skillLevel;
        private int totalLessonCount;

    public CourseForTrainerDto() {
    }

    public CourseForTrainerDto(int courseId, String name, String skillLevel, int totalLessonCount) {
        this.courseId = courseId;
        this.name = name;
        this.skillLevel = skillLevel;
        this.totalLessonCount = totalLessonCount;
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

    public int getTotalLessonCount() {
        return totalLessonCount;
    }

    public void setTotalLessonCount(int totalLessonCount) {
        this.totalLessonCount = totalLessonCount;
    }
}
