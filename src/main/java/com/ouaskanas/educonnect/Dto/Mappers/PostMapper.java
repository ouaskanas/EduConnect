package com.ouaskanas.educonnect.Dto.Mappers;

import com.ouaskanas.educonnect.Dao.Entities.Post;
import com.ouaskanas.educonnect.Dto.PostDto;

public class PostMapper {

    public void UpdateEntityfromDto(Post post,PostDto postDto) {
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setShared(postDto.isShared());
    }

    public PostDto ToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setShared(post.isShared());
        return postDto;
    }

    public Post ToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setShared(postDto.isShared());
        return post;
    }

}
