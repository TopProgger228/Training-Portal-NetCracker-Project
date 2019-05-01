package com.group3.basic.netcracker.backend.dao;

import com.group3.basic.netcracker.backend.entity.News;

import java.time.LocalDate;
import java.util.List;

public interface NewsDao {

    List listNews();
    List listActiveNews();
    void updateNews(int id, String title, LocalDate createDate, String context, boolean isActive);
    void createNews(String title, LocalDate createDate, String context, boolean isActive);
}
