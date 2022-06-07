package com.example.news.service;

import com.example.news.repository.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract class GenericCrudServiceImpl<T> implements GenericCrudService<T> {

    protected abstract GenericRepository<T> getRepository();

    @Override
    public T getById(Long id) {
        return null;
    }

    @Override
    public List<T> getList() {
        return null;
    }

    @Override
    public Page<T> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public T save(T entity) {
        return null;
    }

    @Override
    public T update(Long id, T entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
