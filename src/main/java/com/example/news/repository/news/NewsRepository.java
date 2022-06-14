package com.example.news.repository.news;

import com.example.news.entity.news.News;
import com.example.news.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends GenericRepository<News> {
}
