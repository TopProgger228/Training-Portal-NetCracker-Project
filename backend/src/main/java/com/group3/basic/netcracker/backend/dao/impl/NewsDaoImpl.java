package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.NewsDao;
import com.group3.basic.netcracker.backend.entity.News;
import com.group3.basic.netcracker.backend.util.rowmapper.NewsRowMapper;
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

    @Autowired NewsDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List listNews() {
        String SQL = "SELECT * FROM \"News\"";
        List news = jdbcTemplate.query(SQL, new NewsRowMapper());
        return news;
    }

    @Override
    public List listActiveNews() {
        String SQL = "SELECT * FROM \"News\" WHERE Is_active = TRUE ";
        List news = jdbcTemplate.query(SQL, new NewsRowMapper());
        return news;
    }

    @Override
    public void updateNews(int id, String title, LocalDate createDate, String context, boolean isActive) {
        String SQL = "UPDATE \"News\" SET title = ?, create_date = ?, context = ?, Is_active = ? WHERE id = ?";
        jdbcTemplate.update(SQL, title, createDate, context, isActive, id );
        System.out.println("News updated.");
    }

    @Override
    public void createNews( String title, LocalDate createDate, String context, boolean isActive) {
        String SQL = "INSERT INTO \"News\" ( title, create_date, context, Is_active) VALUES (?,?,?,?)";
        jdbcTemplate.update(SQL, title, createDate, context, isActive );
        System.out.println("News created.");
    }

}
