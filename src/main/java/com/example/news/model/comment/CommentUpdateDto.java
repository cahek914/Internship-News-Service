package com.example.news.model.comment;

import com.example.news.model.TextDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CommentUpdateDto extends TextDto {

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Size(max = 255)
    private String userName;

    @NotNull
    private Long newsId;

}
