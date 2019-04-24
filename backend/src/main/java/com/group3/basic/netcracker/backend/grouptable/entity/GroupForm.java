package com.group3.basic.netcracker.backend.grouptable.entity;

import javax.validation.constraints.NotBlank;

public class GroupForm {
    @NotBlank
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    private int course_id;
    private int[] students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int[] getStudents() {
        return students;
    }

    public void setStudents(int[] students) {
        this.students = students;
    }
}
