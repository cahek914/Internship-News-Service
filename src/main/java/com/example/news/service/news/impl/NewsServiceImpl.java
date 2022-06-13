package com.example.news.service.news.impl;

import com.example.news.entity.News;
import com.example.news.mapper.GenericMapper;
import com.example.news.mapper.news.NewsMapper;
import com.example.news.model.news.NewsFullDto;
import com.example.news.model.news.NewsUpdateDto;
import com.example.news.repository.news.NewsRepository;
import com.example.news.service.GenericCrudServiceImpl;
import com.example.news.service.news.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl extends GenericCrudServiceImpl<News, NewsFullDto, NewsUpdateDto> implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Override
    protected JpaRepository<News, Long> getRepository() {
        return newsRepository;
    }

    @Override
    protected GenericMapper<News, NewsFullDto, NewsUpdateDto> getMapper() {
        return newsMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NewsFullDto> getPage(Pageable page) {
        return newsRepository.findAll(page)
                .map(getMapper()::mapEntityToFullDto);
    }

}
