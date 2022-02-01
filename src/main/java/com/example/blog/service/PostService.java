package com.example.blog.service;

import com.example.blog.dto.PostDto;
import com.example.blog.dto.PostPaginatedResponse;
import com.example.blog.entity.Post;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.mapper.PostMapper;
import com.example.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    PostMapper mapper;

    public PostDto createPost(PostDto postDto)
    {
        Post post = mapper.convertPostDtoToPost(postDto);
        Post savedPost = postRepository.save(post);
        return mapper.convertPostToPostDto(savedPost);
    }

    public PostPaginatedResponse getPosts(int pageNo, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> pageOfPosts = postRepository.findAll(pageable);
        List<Post> posts = pageOfPosts.getContent();

        List<PostDto> content = posts.stream().map(post -> mapper.convertPostToPostDto(post)).collect(Collectors.toList());

        return PostPaginatedResponse.builder()
                .content(content)
                .pageNo(pageOfPosts.getNumber())
                .pageSize(pageOfPosts.getSize())
                .totalElements(pageOfPosts.getTotalElements())
                .totalPages(pageOfPosts.getTotalPages())
                .last(pageOfPosts.isLast())
                .build();
    }

    public PostDto getPostById(Long id)
    {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
        return mapper.convertPostToPostDto(post);
    }

    public PostDto updatePostById(PostDto postDto, Long id)
    {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return mapper.convertPostToPostDto(updatedPost);
    }

    public void deletePostById(Long id)
    {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
        postRepository.delete(post);
    }
}
