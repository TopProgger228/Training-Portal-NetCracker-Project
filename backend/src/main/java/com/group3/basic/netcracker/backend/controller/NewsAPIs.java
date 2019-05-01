package com.group3.basic.netcracker.backend.controller;


import com.group3.basic.netcracker.backend.entity.News;
import com.group3.basic.netcracker.backend.service.NewsService;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class NewsAPIs {
    NewsService newsService;

    @Autowired
    NewsAPIs(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("news/listNews")
    public List getNews() {
        return newsService.listNews();
    }

    @GetMapping("news/listActiveNews")
    public List getActiveNews() {
        return newsService.listActiveNews();
    }

    @PostMapping("news/updateNews")
    public ResponseEntity<?> updateNews(@RequestParam("id") String id,
                                        @RequestParam("title") String title,
                                        @RequestParam("createDate") String createDate,
                                        @RequestParam("context") String context,
                                        @RequestParam("isActive") String isActive) {
        newsService.updateNews(Integer.parseInt(id), title, LocalDate.parse(createDate), context, Boolean.parseBoolean(isActive));

        return new ResponseEntity<>(new ResponseMessage("News updated successfully!"), HttpStatus.CREATED);
    }

    @PostMapping("news/createNews")
    public ResponseEntity<?> createNews(@RequestParam("title") String title,
                                        @RequestParam("context") String context,
                                        @RequestParam("isActive") String isActive) {
        newsService.createNews( title, LocalDate.now(), context, Boolean.parseBoolean(isActive));
        return new ResponseEntity<>(new ResponseMessage("News created successfully!"), HttpStatus.CREATED);
    }


}
