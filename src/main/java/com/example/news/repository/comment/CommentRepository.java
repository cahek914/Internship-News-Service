package com.example.news.repository.comment;

import com.example.news.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.news.id = :newsId")
    List<Comment> getCommentsByNewsId(@Param("newsId") Long newsId);

}
