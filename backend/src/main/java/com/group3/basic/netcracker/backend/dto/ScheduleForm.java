package com.group3.basic.netcracker.backend.dto;

import javax.validation.constraints.NotBlank;

public class ScheduleForm {

    @NotBlank
    private int user_id;
    @NotBlank
    private int time_slot_id;

    private boolean is_choosen;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTime_slot_id() {
        return time_slot_id;
    }

    public void setTime_slot_id(int time_slot_id) {
        this.time_slot_id = time_slot_id;
    }

    public boolean isIs_choosen() {
        return is_choosen;
    }

    public void setIs_choosen(boolean is_choosen) {
        this.is_choosen = is_choosen;
    }
}
