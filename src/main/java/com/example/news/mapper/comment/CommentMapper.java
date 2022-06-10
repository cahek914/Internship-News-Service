package com.example.news.mapper.comment;

import com.example.news.entity.Comment;
import com.example.news.mapper.GenericMapper;
import com.example.news.model.comment.CommentFullDto;
import com.example.news.model.comment.CommentUpdateDto;
import com.example.news.service.news.NewsService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = NewsService.class)
public abstract class CommentMapper implements GenericMapper<Comment, CommentFullDto, CommentUpdateDto> {

    @Override
    @Mapping(source = "news.id", target = "newsId")
    public abstract CommentFullDto mapEntityToFullDto(Comment comment);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "news", source = "newsId")
    public abstract Comment mapForSaveEntity(CommentUpdateDto commentUpdateDto);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "news", ignore = true)
    public abstract Comment mapForUpdateEntity(@MappingTarget Comment comment, CommentUpdateDto commentUpdateDto);

}
