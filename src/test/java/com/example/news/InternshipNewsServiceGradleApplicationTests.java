package com.example.news;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@Slf4j
@RequiredArgsConstructor
@SpringBootTest
class InternshipNewsServiceGradleApplicationTests {

    private final ApplicationContext context;

    @Test
    void contextLoads() {
        String id = context.getId();
        Assertions.assertThat(id).isNotNull();
        log.info("::: Context id : {}", id);
    }

}
