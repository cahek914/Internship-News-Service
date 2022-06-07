package com.example.news.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    private String text;

    private String userName;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

}
