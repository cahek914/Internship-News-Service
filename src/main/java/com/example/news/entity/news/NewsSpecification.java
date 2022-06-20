package com.example.news.entity.news;

import com.example.news.entity.GenericSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class NewsSpecification extends GenericSpecification<News> {

    public NewsSpecification(String filter) {
        super(filter);
    }

    @Override
    public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        String request = "%" + getFilter() + "%";
        return criteriaBuilder.or(
                criteriaBuilder.like(root.get("title"), request),
                criteriaBuilder.like(root.get("text"), request)
        );
    }

}
