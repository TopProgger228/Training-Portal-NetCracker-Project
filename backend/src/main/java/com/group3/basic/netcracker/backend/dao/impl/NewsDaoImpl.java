package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.NewsDao;
import com.group3.basic.netcracker.backend.util.rowmapper.NewsRowMapper;
import com.group3.basic.netcracker.backend.util.sql.NewsDaoQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Transactional
@Repository
public class NewsDaoImpl implements NewsDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    NewsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List listNews() {
        List news = jdbcTemplate.query(NewsDaoQueries.getNewsList, new NewsRowMapper());
        return news;
    }

    @Override
    public List listActiveNews() {
        List news = jdbcTemplate.query(NewsDaoQueries.getActiveNewsList, new NewsRowMapper());
        return news;
    }

    @Override
    public void updateNews(int id, String title, LocalDate createDate, String context, boolean isActive) {
        jdbcTemplate.update(NewsDaoQueries.updateNewsQuery, title, createDate, context, isActive, id);
    }

    @Override
    public void createNews(String title, LocalDate createDate, String context, boolean isActive) {
        jdbcTemplate.update(NewsDaoQueries.createNewsQuery, title, createDate, context, isActive);
    }

}
