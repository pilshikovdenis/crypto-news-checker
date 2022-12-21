package com.pilshikov.news_checker.repository;

import com.pilshikov.news_checker.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    public Optional<News> findFirstByTitle(String title);
}
