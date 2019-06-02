package com.group3.basic.netcracker.backend.dto;

import java.time.LocalDate;

public class CourseWithTrainer {
    public CourseWithTrainer() {
    }

    public CourseWithTrainer(String name, LocalDate start_date, LocalDate end_date, String info, String skill_level, int qty_per_week, String fname, String lname, String username, String email) {
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.info = info;
        this.skill_level = skill_level;
        this.qty_per_week = qty_per_week;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.email = email;
    }

    private String name;
    private LocalDate start_date;
    private LocalDate end_date;
    private String info;
    private String skill_level;
    private int qty_per_week;
    private String fname;
    private String lname;
    private String username;
    private String email;

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

    public int getQty_per_week() {
        return qty_per_week;
    }

    public void setQty_per_week(int qty_per_week) {
        this.qty_per_week = qty_per_week;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
