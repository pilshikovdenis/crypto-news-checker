package com.pilshikov.news_checker.repository;

import com.pilshikov.news_checker.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Integer> {
    public Optional<News> findFirstByTitle(String title);
}
