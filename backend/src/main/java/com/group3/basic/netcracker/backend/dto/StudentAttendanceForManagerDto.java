package com.group3.basic.netcracker.backend.dto;

import java.util.Map;

public class StudentAttendanceForManagerDto {

    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private int totalLessonCount;
    private Map<String, Integer> lessonsMap;

    public StudentAttendanceForManagerDto() {
    }

    public StudentAttendanceForManagerDto(int id, String userName, String firstName, String lastName, int totalLessonCount, Map<String, Integer> lessonsMap) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalLessonCount = totalLessonCount;
        this.lessonsMap = lessonsMap;
    }

    public int getId() {
        return id;
    }

    public int getTotalLessonCount() {
        return totalLessonCount;
    }

    public void setTotalLessonCount(int totalLessonCount) {
        this.totalLessonCount = totalLessonCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<String, Integer> getLessonsMap() {
        return lessonsMap;
    }

    public void setLessonsMap(Map<String, Integer> lessonsMap) {
        this.lessonsMap = lessonsMap;
    }
}
