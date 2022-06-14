package com.example.news.entity.news;

import com.example.news.entity.news.News;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class NewsSpecification implements Specification<News> {

    private final String filter;

    @Override
    public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        String request = "%" + filter + "%";
        return criteriaBuilder.or(
                criteriaBuilder.like(root.get("title"), request),
                criteriaBuilder.like(root.get("text"), request)
        );
    }

}
