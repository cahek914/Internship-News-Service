package com.example.news.service;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface GenericCrudService<Entity, FullDto, UpdateDto> {

    FullDto getById(Long id);

    Entity getEntity(Long id);

    List<FullDto> getList();

    List<FullDto> searchList(Specification<Entity> specification);

    FullDto save(UpdateDto updateDto);

    FullDto update(Long id, UpdateDto updateDto);

    void delete(Long id);

}
