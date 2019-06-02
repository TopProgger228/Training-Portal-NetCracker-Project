package com.group3.basic.netcracker.backend.controller;

import com.group3.basic.netcracker.backend.service.NewsService;
import com.group3.basic.netcracker.backend.util.authorization.message.response.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Slf4j
public class NewsController {
    private NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("news/listNews")
    public List getNews() {
        log.debug("Got list of news!");

        return newsService.listNews();
    }

    @GetMapping("news/listActiveNews")
    public List getActiveNews() {
        log.debug("Got list of active news!");

        return newsService.listActiveNews();
    }

    @PostMapping("news/updateNews")
    public ResponseEntity<?> updateNews(@RequestParam("id") String id,
                                        @RequestParam("title") String title,
                                        @RequestParam("createDate") String createDate,
                                        @RequestParam("context") String context,
                                        @RequestParam("isActive") String isActive) {
        newsService.updateNews(Integer.parseInt(id), title, LocalDate.parse(createDate),
                context, Boolean.parseBoolean(isActive));

        log.info("News with title - {} is updated", title);

        return new ResponseEntity<>(new ResponseMessage("News updated successfully!"), HttpStatus.OK);
    }

    @PostMapping("news/createNews")
    public ResponseEntity<?> createNews(@RequestParam("title") String title,
                                        @RequestParam("context") String context,
                                        @RequestParam("isActive") String isActive) {
        newsService.createNews(title, LocalDate.now(), context, Boolean.parseBoolean(isActive));

        log.info("News with context - {} is created", context);

        return new ResponseEntity<>(new ResponseMessage("News created successfully!"), HttpStatus.CREATED);
    }


}
