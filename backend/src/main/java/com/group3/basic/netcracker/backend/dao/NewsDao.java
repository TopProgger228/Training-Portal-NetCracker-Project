package com.group3.basic.netcracker.backend.dao;


import java.time.LocalDate;
import java.util.List;

public interface NewsDao {

    List listNews();

    List listActiveNews();

    void updateNews(int id, String title, LocalDate createDate, String context, boolean isActive);

    void createNews(String title, LocalDate createDate, String context, boolean isActive);
}
