package com.group3.basic.netcracker.backend.dao;


import java.util.Date;

public interface UsersTokenDao {
    void createToken(String email, String token, Date expiryDate);
    String getEmailByToken(String token);
    Boolean isTokenExist (String token);
    Boolean isTokenAlive(String token);
    int getIdByEmail(String email);
    void replaceTokenById(int id, String token, Date expiryDate);
}
