package com.group3.basic.netcracker.backend.dto;

public class UserAttendanceDto {

    private int id;
    private String userFirstName;
    private String userLastName;
    private String AttendanceStatus;

    public UserAttendanceDto() {
    }

    public UserAttendanceDto(int id, String userFirstName, String userLastName, String attendanceStatus) {
        this.id = id;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
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

    public String getAttendanceStatus() {
        return AttendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        AttendanceStatus = attendanceStatus;
    }

}
