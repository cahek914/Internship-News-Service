package com.example.news.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenericCrudService<Entity, FullDto, UpdateDto> {

    FullDto getById(Long id);

    Entity getEntity(Long id);

    List<FullDto> getList();

    Page<FullDto> getPage(Pageable pageable);

    FullDto save(UpdateDto updateDto);

    FullDto update(Long id, UpdateDto updateDto);

    void delete(Long id);

}
