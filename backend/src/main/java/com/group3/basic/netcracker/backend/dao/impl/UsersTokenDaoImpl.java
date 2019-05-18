package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.UsersTokenDao;
import com.group3.basic.netcracker.backend.entity.UsersToken;
import com.group3.basic.netcracker.backend.util.rowmapper.UsersTokenRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Transactional
@Repository
public class UsersTokenDaoImpl implements UsersTokenDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    UsersTokenDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createToken(String email, String token, Date expiryDate) {
        String SQL = "INSERT INTO \"UsersToken\" (email, token, expiry_date) VALUES (?,?,?)";

        jdbcTemplate.update(SQL, email, token, new java.sql.Date(expiryDate.getTime()).toLocalDate());
        System.out.println("UsersToken successfully created.\nEmail: " + email + "\nToken: " + token + "\nExpiration date: " + new java.sql.Date(expiryDate.getTime()).toLocalDate());

    }

    @Override
    public String getEmailByToken(String token) {
        if (isTokenExist(token)) {
            if(isTokenAlive(token)) {
                String SQL = "SELECT email FROM \"UsersToken\" WHERE token = '" + token + "';";
                String email = (String) jdbcTemplate.queryForObject(SQL, new Object[]{}, String.class);
                return email;
            }else return "Time to live for url out";
        } else return "Current URL does not exist";
    }

    @Override
    public Boolean isTokenExist(String token) {
        String SQL = "SELECT * FROM \"UsersToken\" WHERE token = ?;";
        try {
            jdbcTemplate.queryForObject(SQL, new Object[]{token}, new UsersTokenRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean isTokenAlive(String token) {
        java.util.Date date;
        String SQL = "SELECT expiry_date FROM \"UsersToken\" WHERE token = '" + token + "';";
        date = new java.util.Date(jdbcTemplate.queryForObject(SQL, java.sql.Date.class).getTime());
        if (date.compareTo(new Date(System.currentTimeMillis())) > 0)
            return true;
        return false;
    }


    @Override
    public int getIdByEmail(String email) {

        String SQL = "SELECT * FROM \"UsersToken\" WHERE email = '" + email + "';";
        UsersToken usersToken;
        try {
            usersToken = (UsersToken) jdbcTemplate.queryForObject(SQL, new Object[]{}, new UsersTokenRowMapper());

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return usersToken.getId();
    }

    @Override
    public void replaceTokenById(int id, String token, Date expiryDate) {
        try {
            String SQL = "UPDATE \"UsersToken\" SET token = ?, expiry_date = ? WHERE id = ?";

            jdbcTemplate.update(SQL, token, new java.sql.Date(expiryDate.getTime()).toLocalDate(), id);
            System.out.println("UsersToken successfully created.\n" + "\nToken: " + token + "\nExpiration date: " + new java.sql.Date(expiryDate.getTime()).toLocalDate());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
