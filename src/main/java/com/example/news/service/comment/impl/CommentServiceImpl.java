package com.example.news.service.comment.impl;

import com.example.news.entity.Comment;
import com.example.news.mapper.GenericMapper;
import com.example.news.mapper.comment.CommentMapper;
import com.example.news.model.comment.CommentFullDto;
import com.example.news.model.comment.CommentUpdateDto;
import com.example.news.repository.comment.CommentRepository;
import com.example.news.service.GenericCrudServiceImpl;
import com.example.news.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends GenericCrudServiceImpl<Comment, CommentFullDto, CommentUpdateDto> implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    protected JpaRepository<Comment, Long> getRepository() {
        return commentRepository;
    }

    @Override
    protected GenericMapper<Comment, CommentFullDto, CommentUpdateDto> getMapper() {
        return commentMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentFullDto> getCommentsByNewsId(Long newsId) {
        return commentRepository.getCommentsByNewsId(newsId)
                .stream()
                .map(commentMapper::mapEntityToFullDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CommentFullDto> getPage(Long newsId, Pageable page) {
        return commentRepository.getPageByNewsId(newsId, page)
                .map(getMapper()::mapEntityToFullDto);
    }

}
