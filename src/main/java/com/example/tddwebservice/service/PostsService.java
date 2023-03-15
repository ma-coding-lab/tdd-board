package com.example.tddwebservice.service;

import static java.util.stream.Collectors.toList;

import com.example.tddwebservice.domain.posts.Posts;
import com.example.tddwebservice.domain.posts.PostsRepository;
import com.example.tddwebservice.web.dto.PostsListResponseDto;
import com.example.tddwebservice.web.dto.PostsResponseDto;
import com.example.tddwebservice.web.dto.PostsSaveRequestDto;
import com.example.tddwebservice.web.dto.PostsUpdateRequestDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(final Long id, final PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(final Long id) {
        Posts entity = postsRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
            .map(PostsListResponseDto::new)
            .collect(toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
        );

        postsRepository.delete(posts);
    }
}
