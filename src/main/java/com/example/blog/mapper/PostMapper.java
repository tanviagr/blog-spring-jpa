package com.example.blog.mapper;

import com.example.blog.dto.PostDto;
import com.example.blog.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    public Post convertPostDtoToPost(final PostDto postDto);
    public PostDto convertPostToPostDto(final Post post);
}
