package com.pilshikov.news_checker.job;


import com.pilshikov.news_checker.model.News;
import com.pilshikov.news_checker.service.NewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ParseLatestNewsTask {
    private final String URL = "https://forklog.com/news";

    @Autowired
    private NewsService newsService;

    @Scheduled(fixedDelay = 10000)
    public void runParsing() {
        try {
            // get html page
            Document document = Jsoup.connect(URL)
                    .userAgent("Mozilla")
                    .timeout(5000)
                    .referrer("https://www.google.ru/")
                    .get();

            // get all html containers with news description
            Elements newsBlocks = document.select(".post_item");

            // parse each html block to find news data
            for (Element elem: newsBlocks) {
                String title = elem.select(".text_blk p").text();
                if (newsService.isExists(title)) continue;


                String short_text = elem.select(".text_blk .post_excerpt").text();

                Element link = elem.select("a").first();
                assert link != null;
                String url = link.attr("href");

                System.out.println("title : " + title);
                System.out.println("short_text: " + short_text);
                System.out.println("url: " + url);

                News news = new News(title, short_text, url);
                newsService.save(news);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
