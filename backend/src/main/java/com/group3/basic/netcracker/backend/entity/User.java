package com.group3.basic.netcracker.backend.entity;


import java.time.LocalDate;
import java.util.Arrays;


public class User {

    private int id;
    private String username;
    private String role;
    private String fname;
    private String lname;
    private String email;
    private String pass;
    private LocalDate created_at;
    private byte[] photo = null;
    private int manager_id;

    public User() {
    }

    public User(int id, String username, String role, String fname, String lname,
                String email, String pass, LocalDate created_at, byte[] photo, int manager_id) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pass = pass;
        this.created_at = created_at;
        this.photo = photo;
        this.manager_id = manager_id;
    }

    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.role = user.role;
        this.fname = user.fname;
        this.lname = user.lname;
        this.email = user.email;
        this.pass = user.pass;
        this.created_at = user.created_at;
        this.photo = user.photo;
        this.manager_id = user.manager_id;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", id=" + id +
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }
}
