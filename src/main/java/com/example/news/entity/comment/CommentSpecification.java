package com.example.news.entity.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class CommentSpecification implements Specification<Comment> {

    private final Long newsId;
    private final String filter;

    @Override
    public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        String request = "%" + filter + "%";
        return criteriaBuilder.and(
                criteriaBuilder.or(
                        criteriaBuilder.like(root.get("userName"), request),
                        criteriaBuilder.like(root.get("text"), request)
                ),
                criteriaBuilder.equal(root.join("news").get("id"), newsId)
        );
    }

}
