package com.group3.basic.netcracker.backend.dto;

public class ScheduleWithInfo {
    private String courseName;
    private String studentFname;
    private String studentLname;
    private String timeslotStart_time;
    private String timeslotEnd_time;
    private String timeslotWeek_day;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentFname() {
        return studentFname;
    }

    public void setStudentFname(String studentFname) {
        this.studentFname = studentFname;
    }

    public String getStudentLname() {
        return studentLname;
    }

    public void setStudentLname(String studentLname) {
        this.studentLname = studentLname;
    }

    public String getTimeslotStart_time() {
        return timeslotStart_time;
    }

    public void setTimeslotStart_time(String timeslotStart_time) {
        this.timeslotStart_time = timeslotStart_time;
    }

    public String getTimeslotEnd_time() {
        return timeslotEnd_time;
    }

    public void setTimeslotEnd_time(String timeslotEnd_time) {
        this.timeslotEnd_time = timeslotEnd_time;
    }

    public String getTimeslotWeek_day() {
        return timeslotWeek_day;
    }

    public void setTimeslotWeek_day(String timeslotWeek_day) {
        this.timeslotWeek_day = timeslotWeek_day;
    }
}
