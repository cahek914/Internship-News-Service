package com.example.news.aspect;

import com.example.news.entity.GenericSpecification;
import com.example.news.model.comment.CommentFullDto;
import com.example.news.model.news.NewsFullDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Aspect
@Component
public class ServiceLoggerAspect {

    @Before("execution(* com.example.news.service..get*(Long))")
    public void beforeGenericGetLong(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        log.info("::: Request to service : {}, method : {}, id : {}",
                signature.getDeclaringType().getSimpleName(), signature.getName(), joinPoint.getArgs()[0]);
    }

    @Around("execution(* com.example.news.service..searchList(..))")
    public Object aroundSearchRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        GenericSpecification specification = (GenericSpecification) joinPoint.getArgs()[0];
        log.info("::: Request search : {}", specification.getFilter());
        List proceed = (List) joinPoint.proceed();
        log.info("::: Total find elements : {}", proceed.size());
        return proceed;
    }

    @Around("target(com.example.news.service.news.NewsService+) && execution(* save(..))")
    public Object aroundNewsSaveRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        NewsFullDto proceed = (NewsFullDto) joinPoint.proceed();
        log.info("::: Saved news id : {}, date : {}", proceed.getId(), proceed.getDate());
        return proceed;
    }

    @Around("target(com.example.news.service.comment.CommentService+) && execution(* save(..))")
    public Object aroundCommentSaveRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        CommentFullDto proceed = (CommentFullDto) joinPoint.proceed();
        log.info("::: Saved comment id : {}, news id : {}, date : {}, user name : {}",
                proceed.getId(), proceed.getNewsId(), proceed.getDate(), proceed.getUserName());
        return proceed;
    }

    @AfterReturning(value = "target(com.example.news.service.news.NewsService+) && execution(* update(..))",
    returning = "fullDto")
    public void afterReturningNewsUpdate(NewsFullDto fullDto) {
        log.info("::: News is updated, id : {}", fullDto.getId());
    }

    @AfterReturning(value = "target(com.example.news.service.comment.CommentService+) && execution(* update(..))",
            returning = "fullDto")
    public void afterReturningCommentUpdate(CommentFullDto fullDto) {
        log.info("::: Comment is updated, id : {}, news id : {}", fullDto.getId(), fullDto.getNewsId());
    }

    @Around("execution(* com.example.news.service..delete(Long))")
    public Object aroundDeleteRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("::: Is going to delete entity with id : {}", joinPoint.getArgs()[0]);
        Object proceed = joinPoint.proceed();
        log.info("::: Entity is deleted.");
        return proceed;
    }

    @AfterThrowing(pointcut = "execution(* com.example.news.service..*(..)))",
    throwing = "exc")
    public void afterThrowingService(JoinPoint joinPoint, Throwable exc) {
        Signature signature = joinPoint.getSignature();
        log.info("::: Request to service : {}, method : {}",
                signature.getDeclaringType().getSimpleName(), signature.getName());
        log.error("::: Exception message : {}, class : {}", exc.getMessage(), exc.getClass().getCanonicalName());
    }

}
