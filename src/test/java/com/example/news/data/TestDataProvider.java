package com.example.news.data;

import com.example.news.model.comment.CommentUpdateDto;
import com.example.news.model.news.NewsUpdateDto;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
        NewsUpdateDto newsUpdateDto = new NewsUpdateDto();
        newsUpdateDto.setDate(LocalDateTime.now().minusHours((long) (Math.random() * 100)));
        newsUpdateDto.setTitle(RandomString.make(DEFAULT_TEXT_FIELD_LENGTH));
        newsUpdateDto.setText(RandomString.make(MAX_TEXT_LENGTH));
        return newsUpdateDto;
    }

    public CommentUpdateDto getCommentUpdateDto() {
        CommentUpdateDto commentUpdateDto = new CommentUpdateDto();
        commentUpdateDto.setDate(LocalDateTime.now().minusHours((long) (Math.random() * 100)));
        commentUpdateDto.setUserName(RandomString.make(DEFAULT_TEXT_FIELD_LENGTH));
        commentUpdateDto.setText(RandomString.make(MAX_TEXT_LENGTH));
        return commentUpdateDto;
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

}
