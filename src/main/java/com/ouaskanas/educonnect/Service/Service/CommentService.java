package com.ouaskanas.educonnect.Service.Service;

import com.ouaskanas.educonnect.Dao.Entities.Comment;
import com.ouaskanas.educonnect.Dao.Entities.Post;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.CommentRepository;
import com.ouaskanas.educonnect.Dao.Repositories.PostRepository;
import com.ouaskanas.educonnect.Dto.CommentDto;
import com.ouaskanas.educonnect.Mappers.CommentMapper;
import com.ouaskanas.educonnect.Security.UserDetailService;
import com.ouaskanas.educonnect.Service.Manager.CommentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CommentService implements CommentManager {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserDetailService userDetailsService;


    @Override
    public List<CommentDto> getCommentFromPost(int post_id) {
        Post post =postRepository.findById(post_id).get();
        List<CommentDto> commentDtos = new ArrayList<>();
        List<Comment> comments = post.getCommentList();
        for (Comment comment : comments) {
            commentDtos.add(commentMapper.toDto(comment));
        }
        return commentDtos;
    }

    //must add author
    @Override
    public CommentDto postComment(int post_id, Comment comment) {
        Post post =postRepository.findById(post_id).get();
        comment.setAuthor(userDetailsService.getCurrentUser());
        comment.setPost(post);
        commentRepository.save(comment);
        post.addComment(comment);
        return commentMapper.toDto(comment);
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
