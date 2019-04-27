package com.group3.basic.netcracker.backend.entity;

import java.time.LocalDate;

public class Course {
    private int id;
    private String name;
    private LocalDate start_date;
    private LocalDate end_date;
    private String info;
    private String skill_level;
    private String learn_direction;
    private int user_id;
    private int qty_per_week;

    public Course() {
    }

    public Course(int id, String name, LocalDate start_date, LocalDate end_date, String info, String skill_level, String learn_direction, int user_id, int qty_per_week) {
        this.id = id;
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.info = info;
        this.skill_level = skill_level;
        this.learn_direction = learn_direction;
        this.user_id = user_id;
        this.qty_per_week = qty_per_week;
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

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSkill_level() {
        return skill_level;
    }

    public void setSkill_level(String skill_level) {
        this.skill_level = skill_level;
    }

    public String getLearn_direction() {
        return learn_direction;
    }

    public void setLearn_direction(String learn_direction) {
        this.learn_direction = learn_direction;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQty_per_week() {
        return qty_per_week;
    }

    public void setQty_per_week(int qty_per_week) {
        this.qty_per_week = qty_per_week;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", info='" + info + '\'' +
                ", skill_level='" + skill_level + '\'' +
                ", learn_direction='" + learn_direction + '\'' +
                ", user_id=" + user_id +
                ", qty_per_week=" + qty_per_week +
                '}';
    }
}
