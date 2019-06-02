package com.group3.basic.netcracker.backend.util.rowmapper;

import com.group3.basic.netcracker.backend.entity.News;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class NewsRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        News news = new News();
        news.setId(resultSet.getInt("id"));
        news.setTitle(resultSet.getString("title"));
        news.setContext(resultSet.getString("context"));
        news.setCreateDate(resultSet.getObject("create_date", LocalDate.class));
        news.setIsActive(resultSet.getBoolean("Is_active"));

        return news;
    }
}
