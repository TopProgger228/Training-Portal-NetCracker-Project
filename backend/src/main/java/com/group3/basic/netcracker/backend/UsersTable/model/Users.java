package com.group3.basic.netcracker.backend.UsersTable.model;


import java.time.LocalDate;
import java.util.Arrays;


public class Users {


    private String username;

    private long role_id;

    private String fname;

    private String lname;

    private String email;

    private String pass;

    private LocalDate created_at;

    private byte[] photo = null;

    private int id;

    /*
        @Column(name = "active")
        private boolean active;
    */
    public Users() {
    }

    public Users(String username, long role_id, String fname, String lname, String email, String pass, LocalDate created_at, byte[] photo, int id) {
        this.username = username;
        this.role_id = role_id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pass = pass;
        this.created_at = created_at;
        this.photo = photo;
        this.id = id;
    }



    @Override
    public String toString() {
        return "Users{" +
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

    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
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
}
