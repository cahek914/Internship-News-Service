package com.example.news.controller.comment;

import com.example.news.controller.GenericCrudController;
import com.example.news.entity.Comment;
import com.example.news.model.comment.CommentFullDto;
import com.example.news.model.comment.CommentUpdateDto;
import com.example.news.service.GenericCrudService;
import com.example.news.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController extends GenericCrudController<Comment, CommentFullDto, CommentUpdateDto> {

    private final CommentService commentService;

    @Override
    public GenericCrudService<Comment, CommentFullDto, CommentUpdateDto> getService() {
        return commentService;
    }

    @GetMapping
    public ResponseEntity<Page<CommentFullDto>> getPage(@RequestParam @NotNull Long newsId,
                                                        @RequestParam(required = false) Pageable page) {
        if (Objects.isNull(page)) {
            return ResponseEntity.ok(new PageImpl<>(commentService.getCommentsByNewsId(newsId)));
        }
        return ResponseEntity.ok(commentService.getPage(newsId, page));
    }

}