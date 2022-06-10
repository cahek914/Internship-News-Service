package com.example.news.model.comment;

import com.example.news.model.DtoId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CommentFullDto extends CommentUpdateDto implements DtoId {

    private Long id;

}
