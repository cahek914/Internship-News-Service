package com.example.news.model.news;

import com.example.news.model.DtoId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class NewsFullDto extends NewsUpdateDto implements DtoId {

    private Long id;

}
