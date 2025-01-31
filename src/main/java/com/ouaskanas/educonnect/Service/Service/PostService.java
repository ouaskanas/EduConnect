package com.ouaskanas.educonnect.Service.Service;
import com.ouaskanas.educonnect.Dao.Entities.Post;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.PostRepository;
import com.ouaskanas.educonnect.Dto.PostOutput;
import com.ouaskanas.educonnect.Mappers.PostMapper;
import com.ouaskanas.educonnect.Dto.PostDto;
import com.ouaskanas.educonnect.Service.Manager.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PostService implements PostManager {

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final PostMapper postMapper;

    @Autowired
    private UserService userService;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
        this.postMapper = new PostMapper();
    }

    @Override
    public PostOutput getPost(int id) {
        return postMapper.postOutput(postRepository.findById(id).get());
    }

    @Override
    public List<PostOutput> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostOutput> postOutputs = new ArrayList<>();
        for (Post post : posts) {
            postOutputs.add(postMapper.postOutput(post));
        }
        return postOutputs;
    }

    @Override
    public void deletePost(int id) {
        var post = postRepository.findById(id);
        postRepository.deleteById(id);
    }
    @Override
    public PostOutput addPost(PostDto dto) {
        User user = userService.CurrentUser();
        dto.setAuthor_id(user.getUser_id());
        Post post = postMapper.ToEntity(dto);
        postRepository.save(post);
        return postMapper.postOutput(post);
    }
    @Override
    public PostOutput updatePost(int id, PostDto dto) {
        var post = postRepository.findById(id).get();
        postMapper.UpdateEntityfromDto(post, dto);
        postRepository.save(post);
        return postMapper.postOutput(post);
    }
    @Override
    public PostOutput getPostByTitle(String title) {
        return postMapper.postOutput(postRepository.findByTitle(title));
    }
}
