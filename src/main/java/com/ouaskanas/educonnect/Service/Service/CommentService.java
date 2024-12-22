package com.ouaskanas.educonnect.Service.Service;

import com.ouaskanas.educonnect.Dao.Entities.Comment;
import com.ouaskanas.educonnect.Dao.Entities.Post;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.CommentRepository;
import com.ouaskanas.educonnect.Dao.Repositories.PostRepository;
import com.ouaskanas.educonnect.Service.Manager.CommentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentService implements CommentManager {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public List<Comment> getCommentFromPost(int post_id) {
        Post post =postRepository.findById(post_id).get();
        return post.getCommentList();
    }

    //must add author
    @Override
    public Comment postComment(int post_id, Comment comment) {
        Post post =postRepository.findById(post_id).get();
        comment.setPost(post);
        User user = null;
        //todo:add authenticated user
        comment.setAuthor(user);
        post.getCommentList().add(comment);
        return commentRepository.save(comment);
    }

    @Override
    public boolean deleteComment(int comment_id, int post_id) {
        if(commentRepository.existsById(comment_id) && postRepository.existsById(post_id)){
            Post post =postRepository.findById(post_id).get();
            post.getCommentList().remove(comment_id);
            postRepository.delete(post);
            return true;
        }
       return false;
    }
}
