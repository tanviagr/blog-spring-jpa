package com.example.blog.mapper;

import com.example.blog.dto.CommentDto;
import com.example.blog.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    public Comment convertCommentDtoToComment(final CommentDto commentDto);
    public CommentDto convertCommentToCommentDto(final Comment comment);
}
