package com.ouaskanas.educonnect.Service.Service;

import com.ouaskanas.educonnect.Dao.Entities.Comment;
import com.ouaskanas.educonnect.Dao.Entities.Post;
import com.ouaskanas.educonnect.Dao.Repositories.CommentRepository;
import com.ouaskanas.educonnect.Dao.Repositories.PostRepository;
import com.ouaskanas.educonnect.Service.Manager.CommentManager;

import java.util.List;

public class CommentService implements CommentManager {

    private PostRepository postRepository;
    private CommentRepository commentRepository;


    @Override
    public List<Comment> getCommentFromPost(long post_id) {
        Post post =postRepository.findById(post_id).get();
        return post.getCommentList();
    }

    //must add author
    @Override
    public Comment postComment(long post_id, Comment comment) {
        Post post =postRepository.findById(post_id).get();
        comment.setPost(post);
        post.getCommentList().add(comment);
        return commentRepository.save(comment);
    }

    @Override
    public boolean deleteComment(long comment_id, long post_id) {
        if(commentRepository.existsById(comment_id) && postRepository.existsById(post_id)){
            Post post =postRepository.findById(post_id).get();
            post.getCommentList().remove(comment_id);
            postRepository.delete(post);
            return true;
        }
       return false;
    }
}
