package com.group3.basic.netcracker.backend.service;

import com.group3.basic.netcracker.backend.entity.News;

import java.time.LocalDate;
import java.util.List;

public interface NewsService {

    List listNews();
    List listActiveNews();

    void updateNews(int id, String title, LocalDate createDate, String context, boolean isActive);
    void createNews(String title, LocalDate createDate, String context, boolean isActive);
}
