package com.example.blog.service;

import com.example.blog.dto.CommentDto;
import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.exception.BlogExceptionClass;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.mapper.CommentMapper;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentMapper mapper;

    public CommentDto createComment(CommentDto commentDto, Long id)
    {
        Comment comment = mapper.convertCommentDtoToComment(commentDto);
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
        comment.setPost(post);
        return mapper.convertCommentToCommentDto(commentRepository.save(comment));
    }

    public Set<CommentDto> getAllCommentsForAPost(Long id)
    {
        List<Comment> comments = commentRepository.findByPostId(id);
        return comments.stream().map(comment -> mapper.convertCommentToCommentDto(comment)).collect(Collectors.toSet());
    }

    public CommentDto getCommentByCommentId(Long postId, Long commentId)
    {
        Comment comment = this.basicChecksForPostAndComment(postId, commentId);
        return mapper.convertCommentToCommentDto(comment);
    }

    public CommentDto updateCommentByCommentId(CommentDto commentDto, Long postId, Long commentId)
    {
        Comment comment = this.basicChecksForPostAndComment(postId, commentId);

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment savedComment = commentRepository.save(comment);
        return mapper.convertCommentToCommentDto(comment);
    }

    public void deleteCommentByCommentId(Long postId, Long commentId)
    {
        Comment comment = this.basicChecksForPostAndComment(postId, commentId);

        commentRepository.delete(comment);
    }

    private Comment basicChecksForPostAndComment(Long postId, Long commentId)
    {
        Post postById = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", postId.toString()));

        Comment commentById = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId.toString()));

        //this is business logic, so return a custom exception
        if(!commentById.getPost().getId().equals(postById.getId()))
            throw new BlogExceptionClass(HttpStatus.BAD_REQUEST, "Comment with id = "+commentId+" does not belong to post with id = "+postId);

        return commentById;
    }
}
