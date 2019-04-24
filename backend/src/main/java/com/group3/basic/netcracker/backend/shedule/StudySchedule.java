package com.group3.basic.netcracker.backend.shedule;

public class StudySchedule {
    int id;
    int courseId;
    int userId;
    int timeSlotId;
    boolean isChoosen;

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourse(int courseId) {
        this.courseId = courseId;
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
