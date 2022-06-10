package com.example.news.model.comment;

import com.example.news.model.DtoText;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class CommentUpdateDto implements DtoText {

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Size(max = 2048)
    private String text;

    @NotNull
    @Size(max = 255)
    private String userName;

    @NotNull
    private Long newsId;

}
