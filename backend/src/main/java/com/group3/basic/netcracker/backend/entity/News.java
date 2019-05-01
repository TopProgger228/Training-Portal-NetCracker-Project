package com.group3.basic.netcracker.backend.entity;

import java.time.LocalDate;

public class News {

    private int id;
    private String title;
    private String context;
    private LocalDate createDate;
    private boolean isActive;

    public News(){};

    public News(int id, String title, String context, LocalDate createDate, boolean isActive) {
        this.id = id;
        this.title = title;
        this.context = context;
        this.createDate = createDate;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setId(int id) {
        this.id = id;
    }
}
