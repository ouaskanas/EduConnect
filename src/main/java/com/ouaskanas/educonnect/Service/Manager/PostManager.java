package com.ouaskanas.educonnect.Service.Manager;

import com.ouaskanas.educonnect.Dao.Entities.Post;
import com.ouaskanas.educonnect.Dto.PostDto;

import java.util.List;

public interface PostManager {
    public Post getPost(int id);
    public List<Post> getAllPosts();
    public void deletePost(int id);
    public Post addPost(PostDto post);
    public Post updatePost(int id,PostDto post);
    public Post getPostByTitle(String title);
}
