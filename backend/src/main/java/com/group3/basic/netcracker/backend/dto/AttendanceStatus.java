package com.group3.basic.netcracker.backend.dto;

public enum AttendanceStatus {
    PRESENT("Present"),
    ABSENT_DUE_TO_BUSINESS_TRIP("Absent due to business trip"),
    ABSENT_WITHOUT_REASON("Absent without reason"),
    ABSENT_DUE_TO_SICK_LEAVE("Absent due to sick leave"),
    ABSENT_DUE_TO_PROJECT_ACTIVITIES("Absent due to project activities");

    private String title;

    AttendanceStatus(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
