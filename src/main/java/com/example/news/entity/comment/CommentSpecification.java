package com.example.news.entity.comment;

import com.example.news.entity.GenericSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CommentSpecification extends GenericSpecification<Comment> {

    private final Long newsId;

    public CommentSpecification(Long newsId, String filter) {
        super(filter);
        this.newsId = newsId;
    }

    @Override
    public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        String request = "%" + getFilter() + "%";
        return criteriaBuilder.and(
                criteriaBuilder.or(
                        criteriaBuilder.like(root.get("userName"), request),
                        criteriaBuilder.like(root.get("text"), request)
                ),
                criteriaBuilder.equal(root.join("news").get("id"), newsId)
        );
    }

}
