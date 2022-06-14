package com.example.news.service;

import com.example.news.exception.GenericServiceException;
import com.example.news.mapper.GenericMapper;
import com.example.news.repository.GenericRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericCrudServiceImpl<Entity, FullDto, UpdateDto> implements GenericCrudService<Entity, FullDto, UpdateDto> {

    protected abstract GenericRepository<Entity> getRepository();

    protected abstract GenericMapper<Entity, FullDto, UpdateDto> getMapper();

    @Override
    @Transactional(readOnly = true)
    public FullDto getById(Long id) {
        return getMapper().mapEntityToFullDto(getEntity(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Entity getEntity(Long id) {
        return getRepository().findById(id).orElseThrow(() -> new GenericServiceException.NotFound(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FullDto> getList() {
        return getRepository().findAll().stream()
                .map(getMapper()::mapEntityToFullDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FullDto> searchList(Specification<Entity> specification) {
        return getRepository().findAll(specification).stream()
                .map(getMapper()::mapEntityToFullDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FullDto save(UpdateDto updateDto) {
        return getMapper().mapEntityToFullDto(
                getRepository().save(
                        getMapper().mapForSaveEntity(updateDto)
                )
        );
    }

    @Override
    @Transactional
    public FullDto update(Long id, UpdateDto updateDto) {
        return getMapper().mapEntityToFullDto(
                getRepository().save(
                        getMapper().mapForUpdateEntity(getEntity(id), updateDto)
                )
        );
    }

    @Override
    @Transactional
    public void delete(Long id) {
        getRepository().delete(getEntity(id));
    }

}
