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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Comment> getCommentsByNewsId(Long newsId) {
        return commentRepository.getCommentsByNewsId(newsId);
    }

}
