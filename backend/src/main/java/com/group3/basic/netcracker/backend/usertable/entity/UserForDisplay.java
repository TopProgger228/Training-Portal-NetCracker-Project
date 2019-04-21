package com.group3.basic.netcracker.backend.usertable.entity;


public class UserForDisplay {

    private String fname;
    private String lname;
    private String username;
    private String email;

    public UserForDisplay() {
    }

    public UserForDisplay(String fname, String lname, String username,
                          String email){

        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "fname='" + fname + '\'' +
                " lname='" + lname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
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
