package com.example.news.repository.comment;

import com.example.news.entity.Comment;
import com.example.news.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends GenericRepository<Comment> {
}
