package com.group3.basic.netcracker.backend.UsersTable.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Arrays;

@Entity
@Table(name = "Users")
public class Users {

    @Column(name = "username")
    private String username;

    @Column(name = "role_id")
    private long role_id = 4;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "email")
    private String email;

    @Column(name = "pass")
    private String pass;

    @Column(name = "created_at")
    private LocalDate created_at = LocalDate.now();

    @Column(name = "photo")
    private byte[] photo = null;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

/*
    @Column(name = "active")
    private boolean active;
*/
    public Users() {
    }

    public Users(String username, String fname, String lname, String email, String pass, byte[] photo) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pass = pass;
        this.photo = photo;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
