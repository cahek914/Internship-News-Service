package com.example.news.service.news;

import com.example.news.entity.news.News;
import com.example.news.entity.news.NewsSpecification;
import com.example.news.mapper.GenericMapper;
import com.example.news.mapper.news.NewsMapper;
import com.example.news.model.comment.CommentFullDto;
import com.example.news.model.comment.CommentUpdateDto;
import com.example.news.model.news.NewsFullDto;
import com.example.news.model.news.NewsUpdateDto;
import com.example.news.service.GenericCrudService;
import com.example.news.service.GenericCrudServiceTest;
import com.example.news.service.comment.CommentService;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ValidationException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class NewsServiceTest extends GenericCrudServiceTest<News, NewsFullDto, NewsUpdateDto, NewsSpecification> {

    @Autowired
    private NewsService newsService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private NewsMapper newsMapper;

    @Override
    protected NewsUpdateDto getUpdateDto() {
        return this.dataProvider.getNewsUpdateDto();
    }

    @Override
    protected GenericCrudService<News, NewsFullDto, NewsUpdateDto> getService() {
        return newsService;
    }

    @Override
    protected GenericMapper<News, NewsFullDto, NewsUpdateDto> getMapper() {
        return newsMapper;
    }

    @Test
    public void updatedNewsTitleShouldBeStoredToDatabase() {

        NewsUpdateDto dtoUpdate = getUpdateDto();
        Long savedId = getService().save(dtoUpdate).getId();

        dtoUpdate.setTitle("some new title");
        NewsFullDto newsFullDto = getService().update(savedId, dtoUpdate);

        assertThat(newsFullDto.getTitle()).isEqualTo(dtoUpdate.getTitle());

    }

    @Test
    public void shouldThrownByValidationExceptionIfEntityTitleIncreaseMaxSize() {

        NewsUpdateDto updateDto = getUpdateDto();
        updateDto.setTitle(RandomString.make(300));
        assertThatThrownBy(() -> getService().save(updateDto))
                .isInstanceOf(ValidationException.class);

    }

    @Test
    public void ifNewsIsDeletedThanAllCommentsShouldBeDeletedToo() {

        // create and save news
        NewsFullDto fullDto = getService().save(getUpdateDto());
        Long newsId = fullDto.getId();
        assertThat(newsId).isNotNull();
        // create comments
        List<CommentUpdateDto> commentUpdateDtoList =
                this.dataProvider.getRandomListOf(this.dataProvider::getCommentUpdateDto, MINIMUM_ARRAY_SIZE, ARRAY_SIZE_DELTA);
        // add news id to comments
        commentUpdateDtoList.forEach(commentUpdateDto -> commentUpdateDto.setNewsId(newsId));
        // save comments to database
        commentUpdateDtoList.forEach(commentService::save);
        // check saved comments
        List<CommentFullDto> commentsByNewsId = commentService.getCommentsByNewsId(newsId);
        assertThat(commentsByNewsId).hasSize(commentUpdateDtoList.size());
        // delete this news
        newsService.delete(newsId);
        // asserts that comments deleted
        commentsByNewsId = commentService.getCommentsByNewsId(newsId);
        assertThat(commentsByNewsId).hasSize(0);

    }

    @Test
    public void searchRequestShouldReturnEntityWithSpecificText() {
        String searchRequest = RandomString.make(10);
        NewsUpdateDto updateDto = getUpdateDto();
        updateDto.setText(searchRequest);
        searchRequestImplementation(updateDto, new NewsSpecification(searchRequest));
    }

    @Test
    public void searchRequestShouldReturnEntityWithSpecificTitle() {
        String searchRequest = RandomString.make(10);
        NewsUpdateDto updateDto = getUpdateDto();
        updateDto.setTitle(searchRequest);
        searchRequestImplementation(updateDto, new NewsSpecification(searchRequest));
    }

}