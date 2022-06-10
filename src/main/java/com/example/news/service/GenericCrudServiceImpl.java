package com.example.news.service;

import com.example.news.exception.GenericServiceException;
import com.example.news.mapper.GenericMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericCrudServiceImpl<Entity, FullDto, UpdateDto> implements GenericCrudService<Entity, FullDto, UpdateDto> {

    protected abstract JpaRepository<Entity, Long> getRepository();

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

//    @Override
//    @Transactional(readOnly = true)
//    public Page<FullDto> getPage(Pageable pageable) {
//        return getRepository().findAll(pageable)
//                .map(getMapper()::mapEntityToFullDto);
//    }

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
