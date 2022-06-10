package com.example.news.model.comment;

import com.example.news.model.DtoText;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class CommentUpdateDto implements DtoText {

    private LocalDateTime date;

    private String text;

    private String userName;

    private Long newsId;

}
