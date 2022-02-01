package com.example.blog.controller;

import com.example.blog.dto.PostDto;
import com.example.blog.dto.PostPaginatedResponse;
import com.example.blog.entity.Post;
import com.example.blog.service.PostService;
import com.example.blog.utils.PostConstants;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.blog.utils.PostConstants.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto)
    {
        PostDto savedPostDto = postService.createPost(postDto);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PostPaginatedResponse> getPosts(
            @RequestParam(value = "pageNo", required = false, defaultValue = DEFAULT_PAGE_NUMBER) int pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortBy", required = false, defaultValue = DEFAULT_SORT_BY_FIELD) String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = DEFAULT_SORT_BY_DIRECTION) String sortDir
    )
    {
        PostPaginatedResponse postPaginatedResponse = postService.getPosts(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postPaginatedResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id)
    {
//        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable("id") Long id)
    {
        return ResponseEntity.ok(postService.updatePostById(postDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") Long id)
    {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity with id = "+id+" deleted successfully", HttpStatus.OK);
    }
}
