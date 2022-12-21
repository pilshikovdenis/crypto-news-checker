package com.pilshikov.news_checker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "news")
@Getter
@Setter
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    @Column(name = "short_text")
    private String shortText;

    private String url;

    public News() {
    }

    public News(String title, String shortText, String url) {
        this.title = title;
        this.shortText = shortText;
        this.url = url;
    }
}
