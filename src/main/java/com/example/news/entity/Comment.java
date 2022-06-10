package com.example.news.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @NotNull
    @Size(max = 2048)
    @Column(length = 2048, nullable = false)
    private String text;

    @NotNull
    @Column(nullable = false)
    private String userName;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

}
