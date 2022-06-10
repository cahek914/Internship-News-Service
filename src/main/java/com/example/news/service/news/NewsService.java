package com.example.news.service.news;

import com.example.news.entity.News;
import com.example.news.model.news.NewsFullDto;
import com.example.news.model.news.NewsUpdateDto;
import com.example.news.service.GenericCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NewsService extends GenericCrudService<News, NewsFullDto, NewsUpdateDto> {

    Page<NewsFullDto> getPage(Pageable page);

}
