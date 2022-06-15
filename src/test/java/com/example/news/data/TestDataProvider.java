package com.example.news.data;

import com.example.news.model.comment.CommentFullDto;
import com.example.news.model.comment.CommentUpdateDto;
import com.example.news.model.news.NewsFullDto;
import com.example.news.model.news.NewsUpdateDto;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Component
public class TestDataProvider {

    private static final int MAX_TEXT_LENGTH = 2048;
    private static final int DEFAULT_TEXT_FIELD_LENGTH = 255;

    public <T> List<T> getRandomListOf(Supplier<T> createDto, Integer minListSize, Integer listSizeDelta) {
        List<T> list = new ArrayList<>();
        for(int i = 0; i < (int) (Math.random() * listSizeDelta) + minListSize; i++) {
            list.add(createDto.get());
        }
        return list;
    }

    public NewsUpdateDto getNewsUpdateDto() {
        return fillNewsDto(new NewsUpdateDto());
    }

    public NewsFullDto getNewsFullDto() {
        NewsFullDto newsFullDto = new NewsFullDto();
        newsFullDto.setId(IdGenerator.getId());
        return fillNewsDto(newsFullDto);
    }

    private <T extends NewsUpdateDto> T fillNewsDto(T newsDto) {
        newsDto.setDate(LocalDateTime.now().minusHours((long) (Math.random() * 100)).truncatedTo(ChronoUnit.MICROS));
        newsDto.setTitle(RandomString.make(DEFAULT_TEXT_FIELD_LENGTH));
        newsDto.setText(RandomString.make(MAX_TEXT_LENGTH));
        return newsDto;
    }

    public CommentUpdateDto getCommentUpdateDto() {
        return fillCommentDto(new CommentUpdateDto());
    }

    public CommentFullDto getCommentFullDto() {
        CommentFullDto commentFullDto = new CommentFullDto();
        commentFullDto.setId(IdGenerator.getId());
        return fillCommentDto(commentFullDto);
    }

    private <T extends CommentUpdateDto> T fillCommentDto(T commentDto) {
        commentDto.setDate(LocalDateTime.now().minusHours((long) (Math.random() * 100)).truncatedTo(ChronoUnit.MICROS));
        commentDto.setUserName(RandomString.make(DEFAULT_TEXT_FIELD_LENGTH));
        commentDto.setText(RandomString.make(MAX_TEXT_LENGTH));
        return commentDto;
    }

    public CommentUpdateDto getCommentUpdateDtoWithNewsId(Long newsId) {
        CommentUpdateDto commentUpdateDto = getCommentUpdateDto();
        commentUpdateDto.setNewsId(newsId);
        return commentUpdateDto;
    }

    public List<CommentUpdateDto> getRandomListOfCommentsForNews(Long newsId, Integer minListSize, Integer listSizeDelta) {
        List<CommentUpdateDto> list = new ArrayList<>();
        for(int i = 0; i < (int) (Math.random() * listSizeDelta) + minListSize; i++) {
            list.add(getCommentUpdateDtoWithNewsId(newsId));
        }
        return list;
    }

    private static class IdGenerator {

        private static Long id = 1L;

        public static Long getId() {
            return id++;
        }

    }

}
