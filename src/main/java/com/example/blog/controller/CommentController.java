package com.example.blog.controller;

import com.example.blog.dto.CommentDto;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/posts")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable(value = "postId") Long id)
    {
        CommentDto savedCommentDto = commentService.createComment(commentDto, id);
        return new ResponseEntity<>(savedCommentDto, HttpStatus.CREATED);
    }

    @GetMapping("{postId}/comments")
    public ResponseEntity<Set<CommentDto>> getAllCommentsForAPost(@PathVariable(value = "postId") Long id)
    {
        Set<CommentDto> commentDtos = commentService.getAllCommentsForAPost(id);
        return ResponseEntity.ok(commentDtos);
    }

    @GetMapping("{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentByCommentId(@PathVariable(value = "postId") Long postId,
                                                            @PathVariable(value = "commentId") Long commentId)
    {
        CommentDto commentDto = commentService.getCommentByCommentId(postId, commentId);
        return ResponseEntity.ok(commentDto);
    }

    @PutMapping("{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(
            @RequestBody CommentDto commentDto,
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "commentId") Long commentId)
    {
        CommentDto savedCommentDto = commentService.updateCommentByCommentId(commentDto, postId, commentId);
        return new ResponseEntity<>(savedCommentDto, HttpStatus.OK);
    }

    @DeleteMapping("{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentByCommentId(@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId)
    {
        commentService.deleteCommentByCommentId(postId, commentId);
        return ResponseEntity.ok("Deleted comment with id = "+commentId+" for post with id = "+postId);
    }
}
