package com.pilshikov.news_checker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 500)
    private String title;

    @Column(length = 500)

    private String shortText;

    @Column(length = 500)
    private String url;

    public News() {
    }

    public News(String title, String shortText, String url) {
        this.title = title;
        this.shortText = shortText;
        this.url = url;
    }
}
