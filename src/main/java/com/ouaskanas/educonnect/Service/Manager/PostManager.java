package com.ouaskanas.educonnect.Service.Manager;

import com.ouaskanas.educonnect.Dao.Entities.Post;
import com.ouaskanas.educonnect.Dto.PostDto;
import com.ouaskanas.educonnect.Dto.PostOutput;

import java.util.List;

public interface PostManager {
    public PostOutput getPost(int id);
    public List<PostOutput> getAllPosts();
    public void deletePost(int id);
    public PostOutput addPost(PostDto post);
    public PostOutput updatePost(int id,PostDto post);
    public PostOutput getPostByTitle(String title);
}
