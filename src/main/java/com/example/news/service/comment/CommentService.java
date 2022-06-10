package com.example.news.service.comment;

import com.example.news.entity.Comment;
import com.example.news.model.comment.CommentFullDto;
import com.example.news.model.comment.CommentUpdateDto;
import com.example.news.service.GenericCrudService;

import java.util.List;

public interface CommentService extends GenericCrudService<Comment, CommentFullDto, CommentUpdateDto> {

    List<Comment> getCommentsByNewsId(Long newsId);

}
