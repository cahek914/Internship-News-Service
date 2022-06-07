package com.example.news.service.comment;

import com.example.news.entity.Comment;
import com.example.news.service.GenericCrudService;
import org.springframework.stereotype.Service;

@Service
public interface CommentService extends GenericCrudService<Comment> {
}
