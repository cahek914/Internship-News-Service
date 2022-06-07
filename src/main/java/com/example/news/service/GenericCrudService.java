package com.example.news.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenericCrudService<T> {

    T getById(Long id);

    List<T> getList();

    Page<T> getPage(Pageable pageable);

    T save(T entity);

    T update(Long id, T entity);

    void delete(Long id);

}
