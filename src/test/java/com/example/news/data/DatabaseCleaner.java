package com.example.news.data;

import com.example.news.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCleaner {

    @Autowired
    private NewsService newsService;

    public void clean() {
        newsService.getList().forEach(news -> newsService.delete(news.getId()));
    }

}
