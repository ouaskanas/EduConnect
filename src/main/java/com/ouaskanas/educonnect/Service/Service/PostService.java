package com.ouaskanas.educonnect.Service.Service;
import com.ouaskanas.educonnect.Dao.Entities.Post;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.PostRepository;
import com.ouaskanas.educonnect.Mappers.PostMapper;
import com.ouaskanas.educonnect.Dto.PostDto;
import com.ouaskanas.educonnect.Service.Manager.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostService implements PostManager {

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
        this.postMapper = new PostMapper();
    }

    @Override
    public Post getPost(int id) {
        return postRepository.findById(id).get();
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void deletePost(int id) {
        var post = postRepository.findById(id);
        postRepository.deleteById(id);
    }
    //must add author
    @Override
    public Post addPost(PostDto dto) {
        User user = null;
        //todo:GetAuthenticatedUser
        dto.setAuthor_id(user.getUser_id());
        Post post = postMapper.ToEntity(dto);
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(int id, PostDto dto) {
        var post = postRepository.findById(id).get();
        postMapper.UpdateEntityfromDto(post, dto);
        return postRepository.save(post);
    }

    @Override
    public Post getPostByTitle(String title) {
        return postRepository.findByTitle(title);
    }
}
