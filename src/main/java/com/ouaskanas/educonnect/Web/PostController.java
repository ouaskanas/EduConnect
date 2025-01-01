package com.ouaskanas.educonnect.Web;

import com.ouaskanas.educonnect.Dao.Entities.Post;
import com.ouaskanas.educonnect.Dao.Repositories.PostRepository;
import com.ouaskanas.educonnect.Dto.PostDto;
import com.ouaskanas.educonnect.Dto.PostOutput;
import com.ouaskanas.educonnect.Service.Manager.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    @Autowired
    private PostManager postManager;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/allposts")
    public List<PostOutput> getAllPosts(){
        return postManager.getAllPosts();
    }

    @GetMapping("/getpost/{id}")
    public PostOutput getPostById(@PathVariable int id){
        return postManager.getPost(id);
    }

    @PostMapping("/createpost")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto post){
        postManager.addPost(post);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @PutMapping("/alterpost/{id}")
    public ResponseEntity<PostDto> alterPost(@PathVariable int id, @RequestBody PostDto post){
        if(!postRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        postManager.updatePost(id, post);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id){
        if(!postRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        postManager.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted post");
    }

    @GetMapping("/getpost/{title}")
    public PostOutput getPostByTitle(@PathVariable String title){
        return postManager.getPostByTitle(title);
    }
}
