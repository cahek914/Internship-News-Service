package com.example.news.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public abstract class GenericSpecification<T> implements Specification<T> {

    @Getter
    private final String filter;

}
