package com.group3.basic.netcracker.backend.entity;

public class Group {
    private int id;
    private String name;
    private int course_id;

    public Group() {
    }

    public Group(int id, String name, int course_id) {
        this.id = id;
        this.name = name;
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course_id=" + course_id +
                '}';
    }

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
}
