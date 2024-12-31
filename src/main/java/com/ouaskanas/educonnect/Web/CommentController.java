package com.ouaskanas.educonnect.Web;

import com.ouaskanas.educonnect.Dao.Entities.Comment;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.CommentRepository;
import com.ouaskanas.educonnect.Dao.Repositories.PostRepository;
import com.ouaskanas.educonnect.Dto.CommentDto;
import com.ouaskanas.educonnect.Mappers.CommentMapper;
import com.ouaskanas.educonnect.Service.Manager.CommentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/comment")
public class CommentController {

    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private CommentManager commentManager;
    @Autowired
    private CommentMapper commentMapper;

    public CommentController(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/{post_id}/allcomments")
    public List<CommentDto> getAllComments(@PathVariable int post_id) {
        return commentManager.getCommentFromPost(post_id);
    }

    @PostMapping("/{post_id}/addcomment")
    public ResponseEntity<CommentDto> addComment(@PathVariable int post_id,
                                              @RequestBody Comment comment) {
        if(!postRepository.existsById(post_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        CommentDto commentDto=commentManager.postComment(post_id, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDto);
    }

    @DeleteMapping("/{post_id}/{comment_id}")
    public ResponseEntity<String> deleteComment(@PathVariable int post_id, @PathVariable int comment_id) {
        if(!postRepository.existsById(post_id) && !commentRepository.existsById(comment_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        commentManager.deleteComment(post_id, comment_id);
        return ResponseEntity.status(HttpStatus.OK).body("Comment Deleted");
    }
}
