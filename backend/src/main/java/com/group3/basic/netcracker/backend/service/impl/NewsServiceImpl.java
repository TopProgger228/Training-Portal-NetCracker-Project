package com.group3.basic.netcracker.backend.service.impl;

import com.group3.basic.netcracker.backend.dao.NewsDao;
import com.group3.basic.netcracker.backend.entity.News;
import com.group3.basic.netcracker.backend.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private NewsDao newsDao;

    @Autowired
    public NewsServiceImpl(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public List listNews() {
        return newsDao.listNews();
    }

    @Override
    public List listActiveNews() {
        return newsDao.listActiveNews();
    }

    @Override
    public void updateNews(int id, String title, LocalDate createDate, String context, boolean isActive) {
        newsDao.updateNews(id, title, createDate, context, isActive);
    }

    @Override
    public void createNews(String title, LocalDate createDate, String context, boolean isActive) {
        newsDao.createNews(title, createDate, context, isActive);
    }

}
