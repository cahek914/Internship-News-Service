package com.example.news.service.news;

import com.example.news.entity.News;
import com.example.news.model.news.NewsFullDto;
import com.example.news.model.news.NewsUpdateDto;
import com.example.news.service.GenericCrudService;

public interface NewsService extends GenericCrudService<News, NewsFullDto, NewsUpdateDto> {
}
