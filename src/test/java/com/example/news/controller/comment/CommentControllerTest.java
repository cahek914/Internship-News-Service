package com.example.news.controller.comment;

import com.example.news.controller.GenericCrudControllerTest;
import com.example.news.data.TestDataProvider;
import com.example.news.entity.Comment;
import com.example.news.mapper.GenericMapper;
import com.example.news.mapper.comment.CommentMapper;
import com.example.news.model.comment.CommentFullDto;
import com.example.news.model.comment.CommentUpdateDto;
import com.example.news.service.GenericCrudService;
import com.example.news.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CommentControllerTest extends GenericCrudControllerTest<Comment, CommentFullDto, CommentUpdateDto> {

    private static final String URL_ROOT = "/comments";

    private static final String URL_ID = "/comments/{id}";

    @Autowired
    private TestDataProvider dataProvider;

    @Autowired
    private CommentMapper commentMapper;

    @MockBean
    private CommentService commentService;

    @Override
    protected Class<CommentFullDto> getFullDtoClass() {
        return CommentFullDto.class;
    }

    @Override
    protected Class<CommentUpdateDto> getUpdateDtoClass() {
        return CommentUpdateDto.class;
    }

    @Override
    protected String getRootUrl() {
        return URL_ROOT;
    }

    @Override
    protected String getUrlWithId() {
        return URL_ID;
    }

    @Override
    protected CommentFullDto getFullDto() {
        CommentFullDto commentFullDto = dataProvider.getCommentFullDto();
        commentFullDto.setNewsId(1L);
        return commentFullDto;
    }

    @Override
    protected GenericMapper<Comment, CommentFullDto, CommentUpdateDto> getMapper() {
        return commentMapper;
    }

    @Override
    protected GenericCrudService<Comment, CommentFullDto, CommentUpdateDto> getService() {
        return commentService;
    }

}