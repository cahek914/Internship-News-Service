package com.example.news.service.news.impl;

import com.example.news.entity.News;
import com.example.news.repository.GenericRepository;
import com.example.news.repository.news.NewsRepository;
import com.example.news.service.GenericCrudServiceImpl;
import com.example.news.service.news.NewsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NewsServiceImpl extends GenericCrudServiceImpl<News> implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    protected GenericRepository<News> getRepository() {
        return newsRepository;
    }

}
