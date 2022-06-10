package com.example.news.service.comment;

import com.example.news.entity.Comment;
import com.example.news.model.comment.CommentFullDto;
import com.example.news.model.comment.CommentUpdateDto;
import com.example.news.service.GenericCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService extends GenericCrudService<Comment, CommentFullDto, CommentUpdateDto> {

    List<CommentFullDto> getCommentsByNewsId(Long newsId);

    Page<CommentFullDto> getPage(Long newsId, Pageable page);

}
