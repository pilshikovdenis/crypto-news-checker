package com.pilshikov.news_checker.service;

import com.pilshikov.news_checker.model.News;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService {
    public void save(News news);
    public boolean isExists(String title);
    public List<News> getAll();
}
