package com.group3.basic.netcracker.backend.dto;


public class UserForDisplay {

    private String fname;
    private String lname;
    private String username;
    private String email;
    private int id;

    public UserForDisplay() {
    }

    public UserForDisplay(String fname, String lname, String username,
                          String email, int id){

        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "fname='" + fname + '\'' +
                " lname='" + lname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", id '" + id + '\'' +
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }


}
