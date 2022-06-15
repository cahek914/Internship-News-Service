package com.example.news.service.comment;

import com.example.news.entity.comment.Comment;
import com.example.news.entity.comment.CommentSpecification;
import com.example.news.mapper.GenericMapper;
import com.example.news.mapper.comment.CommentMapper;
import com.example.news.model.comment.CommentFullDto;
import com.example.news.model.comment.CommentUpdateDto;
import com.example.news.model.news.NewsFullDto;
import com.example.news.model.news.NewsUpdateDto;
import com.example.news.service.GenericCrudService;
import com.example.news.service.GenericCrudServiceTest;
import com.example.news.service.news.NewsService;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommentServiceTest extends GenericCrudServiceTest<Comment, CommentFullDto, CommentUpdateDto, CommentSpecification> {

    @Autowired
    private CommentService commentService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    protected CommentUpdateDto getUpdateDto() {
        // create and save news
        NewsFullDto newsFullDto = newsService.save(this.dataProvider.getNewsUpdateDto());
        // create comment and set news id
        CommentUpdateDto commentUpdateDto = this.dataProvider.getCommentUpdateDto();
        commentUpdateDto.setNewsId(newsFullDto.getId());
        return commentUpdateDto;
    }

    @Override
    protected GenericCrudService<Comment, CommentFullDto, CommentUpdateDto> getService() {
        return commentService;
    }

    @Override
    protected GenericMapper<Comment, CommentFullDto, CommentUpdateDto> getMapper() {
        return commentMapper;
    }

    @Test
    public void getAllCommentsForSpecificNewsIdShouldReturnSpecificComments() {
        // create some news
        List<NewsUpdateDto> newsUpdateDtoList =
                this.dataProvider.getRandomListOf(
                        this.dataProvider::getNewsUpdateDto, MINIMUM_ARRAY_SIZE, ARRAY_SIZE_DELTA
                );
        // store these news to database
        List<NewsFullDto> newsFullDtoList = newsUpdateDtoList.stream()
                .map(newsService::save)
                .collect(Collectors.toList());
        assertThat(newsFullDtoList).hasSize(newsUpdateDtoList.size());
        // create comments for all news
        List<CommentFullDto> commentFullDtoList = newsFullDtoList.stream()
                .flatMap(
                        news -> this.dataProvider
                                .getRandomListOfCommentsForNews(news.getId(), MINIMUM_ARRAY_SIZE, ARRAY_SIZE_DELTA)
                                .stream()
                                .map(commentService::save)
                )
                .collect(Collectors.toList());
        // get some news id
        Long newsId = newsFullDtoList.get(newsFullDtoList.size() / 2).getId();
        // retrieve specific comments by news id
        List<CommentFullDto> commentsByNewsId = commentService.getCommentsByNewsId(newsId);
        assertThat(commentsByNewsId.size()).isEqualTo(
                commentFullDtoList.stream().filter(dto -> dto.getNewsId().equals(newsId)).count());
    }

    @Test
    public void searchRequestShouldReturnEntityWithSpecificUserName() {
        String searchRequest = RandomString.make(10);
        CommentUpdateDto updateDto = getUpdateDto();
        updateDto.setUserName(searchRequest);
        searchRequestImplementation(updateDto, new CommentSpecification(updateDto.getNewsId(), searchRequest));
    }

    @Test
    public void searchRequestShouldReturnEntityWithSpecificText() {
        String searchRequest = RandomString.make(10);
        CommentUpdateDto updateDto = getUpdateDto();
        updateDto.setText(searchRequest);
        searchRequestImplementation(updateDto, new CommentSpecification(updateDto.getNewsId(), searchRequest));
    }

}