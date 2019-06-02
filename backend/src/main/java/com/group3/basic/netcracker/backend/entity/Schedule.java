package com.group3.basic.netcracker.backend.entity;

public class Schedule {
    private int id;
    private int userId;
    private int timeSlotId;
    private boolean isChoosen;

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(int timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public boolean isChoosen() {
        return isChoosen;
    }

    public void setChoosen(boolean isChoosen) {
        this.isChoosen = isChoosen;
    }

    @Override
    public String toString() {
        return "This schedule time slot: " + timeSlotId;
    }

}
