package com.example.news.model.news;

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
public class NewsUpdateDto implements DtoText {

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    @Size(max = 2048)
    private String text;

}
