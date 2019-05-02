package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.UsersTokenDao;
import com.group3.basic.netcracker.backend.entity.UsersToken;
import com.group3.basic.netcracker.backend.util.rowmapper.UsersTokenRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Transactional
@Repository
public class UsersTokenDaoImpl implements UsersTokenDao {
    JdbcTemplate jdbcTemplate;

    @Autowired
    UsersTokenDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createToken(String email, String token, Date expiryDate) {
        String SQL = "INSERT INTO \"UsersToken\" (email, token, expiry_date) VALUES (?,?,?)";

        jdbcTemplate.update(SQL, email, token, new java.sql.Date( expiryDate.getTime()).toLocalDate());
        System.out.println("UsersToken successfully created.\nEmail: " + email + "\nToken: " + token + "\nExpiration date: ");

    }

    @Override
    public String getEmailByToken(String token){
        String SQL = "SELECT * FROM \"UsersToken\" WHERE token = '" + token + "';";
        UsersToken usersToken = (UsersToken) jdbcTemplate.query(SQL, new UsersTokenRowMapper()).get(0);
        return usersToken.getEmail();
    }



}
