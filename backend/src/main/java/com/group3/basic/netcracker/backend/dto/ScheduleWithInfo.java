package com.group3.basic.netcracker.backend.dto;

public class ScheduleWithInfo {
    private int courseId;
    private String courseName;
    private int countVoted;
    private String isScheduled;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCountVoted() {
        return countVoted;
    }

    public void setCountVoted(int countVoted) {
        this.countVoted = countVoted;
    }

    public String getIsScheduled() {
        return isScheduled;
    }

    public void setIsScheduled(String isScheduled) {
        this.isScheduled = isScheduled;
    }
}
