package com.example.tddwebservice.domain.posts;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;
    String title = "title";
    String content = "content";
    String author = "author";

    @AfterEach
    void afterEach() {
        postsRepository.deleteAll();
    }

    @Test
    void saveAndLoad() {
        // given
        postsRepository.save(Posts.builder()
            .title(title)
            .content(content)
            .author("author")
            .build());

        // when
        List<Posts> all = postsRepository.findAll();

        // then
        Posts posts = all.get(0);
        assertEquals(posts.getTitle(), title);
        assertEquals(posts.getContent(), content);
    }

    @Test
    void register_BaseEntity() {
        // given
        LocalDateTime now = LocalDateTime.of(2023, 3, 4, 12, 34, 59);
        postsRepository.save(Posts.builder()
            .title(title)
            .content(content)
            .author(author)
            .build()
        );

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println("## createdAt=" + posts.getCreatedAt() + ", modifiedAt=" + posts.getModifiedAt());
        assertThat(posts.getCreatedAt()).isAfter(now);
        assertThat(posts.getModifiedAt()).isAfter(now);
    }
}
