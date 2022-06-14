package com.example.news.controller.news;

import com.example.news.controller.GenericCrudControllerTest;
import com.example.news.data.TestDataProvider;
import com.example.news.entity.news.News;
import com.example.news.mapper.GenericMapper;
import com.example.news.mapper.news.NewsMapper;
import com.example.news.model.news.NewsFullDto;
import com.example.news.model.news.NewsUpdateDto;
import com.example.news.service.GenericCrudService;
import com.example.news.service.news.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class NewsControllerTest extends GenericCrudControllerTest<News, NewsFullDto, NewsUpdateDto> {

    private static final String URL_ROOT = "/news";

    private static final String URL_ID = "/news/{id}";

    @Autowired
    private TestDataProvider dataProvider;

    @Autowired
    private NewsMapper newsMapper;

    @MockBean
    private NewsService newsService;

    @Override
    protected Class<NewsFullDto> getFullDtoClass() {
        return NewsFullDto.class;
    }

    @Override
    protected Class<NewsUpdateDto> getUpdateDtoClass() {
        return NewsUpdateDto.class;
    }

    @Override
    protected String getRootUrl() {
        return URL_ROOT;
    }

    @Override
    protected String getUrlWithId() {
        return URL_ID;
    }

    @Override
    protected NewsFullDto getFullDto() {
        return dataProvider.getNewsFullDto();
    }

    @Override
    protected GenericMapper<News, NewsFullDto, NewsUpdateDto> getMapper() {
        return newsMapper;
    }

    @Override
    protected GenericCrudService<News, NewsFullDto, NewsUpdateDto> getService() {
        return newsService;
    }

}