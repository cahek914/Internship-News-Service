package com.example.news.controller.news;

import com.example.news.controller.GenericCrudController;
import com.example.news.entity.News;
import com.example.news.model.news.NewsFullDto;
import com.example.news.model.news.NewsUpdateDto;
import com.example.news.service.GenericCrudService;
import com.example.news.service.news.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController extends GenericCrudController<News, NewsFullDto, NewsUpdateDto> {

    private final NewsService newsService;

    @Override
    public GenericCrudService<News, NewsFullDto, NewsUpdateDto> getService() {
        return newsService;
    }

    @GetMapping
    public ResponseEntity<Page<NewsFullDto>> getPage(
            @PageableDefault(sort = "date", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.ok(newsService.getPage(page));
    }

}
