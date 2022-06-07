package com.example.news.service.comment.impl;

import com.example.news.entity.Comment;
import com.example.news.repository.GenericRepository;
import com.example.news.repository.comment.CommentRepository;
import com.example.news.service.GenericCrudServiceImpl;
import com.example.news.service.comment.CommentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentServiceImpl extends GenericCrudServiceImpl<Comment> implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    protected GenericRepository<Comment> getRepository() {
        return commentRepository;
    }

}
