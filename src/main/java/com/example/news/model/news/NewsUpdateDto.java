package com.example.news.model.news;

import com.example.news.model.DtoText;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class NewsUpdateDto implements DtoText {

    private LocalDateTime date;

    //    @Size(max = 255)
    private String title;

    //    @Size(max = 2048)
    private String text;

}
