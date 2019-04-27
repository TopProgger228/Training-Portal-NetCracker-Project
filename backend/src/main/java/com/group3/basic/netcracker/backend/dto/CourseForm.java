package com.group3.basic.netcracker.backend.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class CourseForm {
    @NotBlank
    private String name;

    private String start_date;

    private String end_date;
    @NotBlank
    private String info;
    @NotBlank
    private String skill_level;
    @NotBlank
    private String learn_direction;
    @NotBlank
    private int user_id;
    @NotBlank
    private int qty_per_week;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
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
}
