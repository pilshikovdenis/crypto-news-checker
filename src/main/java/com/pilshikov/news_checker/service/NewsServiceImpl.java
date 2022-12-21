package com.pilshikov.news_checker.service;

import com.pilshikov.news_checker.model.News;
import com.pilshikov.news_checker.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl  implements NewsService{
    @Autowired
    NewsRepository repository;

    @Override
    public void save(News news) {
        repository.save(news);
    }

    @Override
    public boolean isExists(String title) {
        return repository.findFirstByTitle(title).isPresent();
    }

    @Override
    public List<News> getAll() {
        return repository.findAll();
    }
}
