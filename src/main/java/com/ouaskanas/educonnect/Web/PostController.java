package com.ouaskanas.educonnect.Web;

import com.ouaskanas.educonnect.Dao.Entities.Post;
import com.ouaskanas.educonnect.Dto.PostDto;
import com.ouaskanas.educonnect.Service.Manager.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    @Autowired
    private PostManager postManager;

    @GetMapping("/allposts")
    public List<Post> getAllPosts(){
        return postManager.getAllPosts();
    }

    @GetMapping("/getpost/{id}")
    public Post getPostById(@PathVariable int id){
        return postManager.getPost(id);
    }

    @PostMapping("/createpost")
    public String createPost(@RequestBody PostDto post){
        postManager.addPost(post);
        return "redirect:/post/allposts";
    }

    @PutMapping("/alterpost/{id}")
    public String alterPost(@PathVariable int id, @RequestBody PostDto post){
        postManager.updatePost(id, post);
        return "redirect::/api/v1/post/"+id;
    }

    @DeleteMapping("/delete/{id}")
    public String deletePost(@PathVariable int id){
        postManager.deletePost(id);
        return "redirect:/post/allposts";
    }
}
