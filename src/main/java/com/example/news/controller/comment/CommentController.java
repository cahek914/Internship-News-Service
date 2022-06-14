package com.example.news.controller.comment;

import com.example.news.controller.GenericCrudController;
import com.example.news.entity.comment.Comment;
import com.example.news.entity.comment.CommentSpecification;
import com.example.news.model.comment.CommentFullDto;
import com.example.news.model.comment.CommentUpdateDto;
import com.example.news.service.GenericCrudService;
import com.example.news.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

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
    public ResponseEntity<Page<CommentFullDto>> getPage(
            @RequestParam @NotNull Long newsId,
            @PageableDefault(sort = "date", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.ok(commentService.getPage(newsId, page));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CommentFullDto>> searchList(
            @RequestParam @NotNull Long newsId,
            @RequestParam @NotNull String filter) {
        return ResponseEntity.ok(commentService.searchList(new CommentSpecification(newsId, filter)));
    }

}
