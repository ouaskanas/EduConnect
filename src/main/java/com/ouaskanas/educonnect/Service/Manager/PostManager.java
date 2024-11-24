package com.ouaskanas.educonnect.Service.Manager;

import com.ouaskanas.educonnect.Dao.Entities.Post;
import com.ouaskanas.educonnect.Dto.PostDto;

import java.util.List;

public interface PostManager {
    public Post getPost(long id);
    public List<Post> getAllPosts();
    public void deletePost(long id);
    public Post addPost(PostDto post);
    public Post updatePost(long id,PostDto post);
    public Post getPostByTitle(String title);
}
