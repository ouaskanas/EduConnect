package com.ouaskanas.educonnect.Service.Service;

import com.ouaskanas.educonnect.Dao.Entities.Post;
import com.ouaskanas.educonnect.Dao.Repositories.PostRepository;
import com.ouaskanas.educonnect.Dto.Mappers.PostMapper;
import com.ouaskanas.educonnect.Dto.PostDto;
import com.ouaskanas.educonnect.Service.Manager.PostManager;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostService implements PostManager {

    private final PostRepository postRepository;


    private PostMapper postMapper;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post getPost(long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void deletePost(long id) {
        var post = postRepository.findById(id);
        postRepository.deleteById(id);
    }
    //must add author
    @Override
    public Post addPost(PostDto dto) {
        Post post = postMapper.ToEntity(dto);
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(long id, PostDto dto) {
        var post = postRepository.findById(id).get();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setShared(dto.isShared());
        return postRepository.save(post);
    }

    @Override
    public Post getPostByTitle(String title) {
        return postRepository.findByTitle(title);
    }
}
