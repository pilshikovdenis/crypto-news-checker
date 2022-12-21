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
    // url to news source
    private static final String URL = "https://forklog.com/news";
    // delay between every parsing tasks
    private static final int delayBetweenTask = 60000;

    @Autowired
    private NewsService newsService;

    @Scheduled(fixedDelay = delayBetweenTask)
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
                // get title
                String title = elem.select(".text_blk p").text();
                // if news with this title is already exists, skip it
                if (newsService.isExists(title)) continue;

                // get short text
                String short_text = elem.select(".text_blk .post_excerpt").text();

                // get url to source
                Element link = elem.select("a").first();
                assert link != null;
                String url = link.attr("href");

                // create model and save it
                News news = new News(title, short_text, url);
                newsService.save(news);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
