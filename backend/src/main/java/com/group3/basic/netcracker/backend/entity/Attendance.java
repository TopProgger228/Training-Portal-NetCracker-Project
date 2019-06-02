package com.group3.basic.netcracker.backend.entity;

public class Attendance {

    private int missingCount;
    private String missingUser;
    private String course;
    private String reason;

    public int getMissingCount() {
        return missingCount;
    }

    public void setMissingCount(int missingCount) {
        this.missingCount = missingCount;
    }

    public String getMissingUser() {
        return missingUser;
    }

    public void setMissingUser(String missingUser) {
        this.missingUser = missingUser;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Count: " + missingCount + " User: " + missingUser + " Reason: " + reason;
    }
}
