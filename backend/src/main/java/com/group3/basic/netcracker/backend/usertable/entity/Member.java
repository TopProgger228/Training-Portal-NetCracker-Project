package com.group3.basic.netcracker.backend.usertable.entity;

import javax.validation.constraints.NotBlank;

public class Member {

    @NotBlank
    private String fname;

    @NotBlank
    private String lname;

    @NotBlank
    private String role;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
