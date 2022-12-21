package com.pilshikov.news_checker.api;

import com.pilshikov.news_checker.model.News;
import com.pilshikov.news_checker.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping(value = "news/")
    public List<News> showAll() {
        return newsService.getAll();
    }
}
