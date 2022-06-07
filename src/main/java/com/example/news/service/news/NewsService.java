package com.example.news.service.news;

import com.example.news.entity.News;
import com.example.news.service.GenericCrudService;
import org.springframework.stereotype.Service;

@Service
public interface NewsService extends GenericCrudService<News> {
}
