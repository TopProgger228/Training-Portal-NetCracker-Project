package com.group3.basic.netcracker.backend.dto;

public class TrainerSelectorDto {

    private int id;
    private String FirstName;
    private String LastName;

    public TrainerSelectorDto() {
    }

    public TrainerSelectorDto(int id, String firstName, String lastName) {
        this.id = id;
        FirstName = firstName;
        LastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
