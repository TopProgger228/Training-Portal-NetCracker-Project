package com.group3.basic.netcracker.backend.entity;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class UsersToken {


    private static final int EXPIRATION = 60 * 24;

    private int id;
    private String token;
    private String email;
    private Date expiryDate;

    public UsersToken() {
    }

    public UsersToken(String email, String token) {
        this.token = token;
        this.email = email;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }


    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


}
