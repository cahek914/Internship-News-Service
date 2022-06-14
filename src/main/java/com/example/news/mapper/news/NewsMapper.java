package com.example.news.mapper.news;

import com.example.news.entity.news.News;
import com.example.news.mapper.GenericMapper;
import com.example.news.model.news.NewsFullDto;
import com.example.news.model.news.NewsUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class NewsMapper implements GenericMapper<News, NewsFullDto, NewsUpdateDto> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comments", ignore = true)
    public abstract News mapForSaveEntity(NewsUpdateDto newsUpdateDto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comments", ignore = true)
    public abstract News mapForUpdateEntity(@MappingTarget News news, NewsUpdateDto newsUpdateDto);

}
