package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.UsersTokenDao;
import com.group3.basic.netcracker.backend.entity.UsersToken;
import com.group3.basic.netcracker.backend.util.rowmapper.UsersTokenRowMapper;
import com.group3.basic.netcracker.backend.util.sql.TokenDaoQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Transactional
@Repository
public class UsersTokenDaoImpl implements UsersTokenDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersTokenDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createToken(String email, String token, Date expiryDate) {
        jdbcTemplate.update(TokenDaoQueries.createTokenQuery, email, token,
                new java.sql.Date(expiryDate.getTime()).toLocalDate());
    }

    @Override
    public String getEmailByToken(String token) {
        if (isTokenExist(token)) {
            if (isTokenAlive(token)) {
                return jdbcTemplate.queryForObject(TokenDaoQueries.getEmailByTokenQuery,
                        new Object[]{token}, String.class);
            } else return "Time to live for url out";
        } else return "Current URL does not exist";
    }

    @Override
    public Boolean isTokenExist(String token) {
        try {
            jdbcTemplate.queryForObject(TokenDaoQueries.isTokenExistsQuery,
                    new Object[]{token}, new UsersTokenRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean isTokenAlive(String token) {
        java.util.Date date;

        date = new java.util.Date(jdbcTemplate.queryForObject(TokenDaoQueries.isTokenAliveQuery,
                new Object[]{token}, java.sql.Date.class).getTime());

        if (date.compareTo(new Date(System.currentTimeMillis())) > 0)
            return true;
        return false;
    }


    @Override
    public int getIdByEmail(String email) {
        UsersToken usersToken;

        try {
            usersToken = (UsersToken) jdbcTemplate.queryForObject(TokenDaoQueries.getIdByEmailQuery,
                    new Object[]{email}, new UsersTokenRowMapper());

        } catch (Exception e) {
            return -1;
        }
        return usersToken.getId();
    }

    @Override
    public void replaceTokenById(int id, String token, Date expiryDate) {
        try {
            jdbcTemplate.update(TokenDaoQueries.replaceTokenById,
                    token, new java.sql.Date(expiryDate.getTime()).toLocalDate(), id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
