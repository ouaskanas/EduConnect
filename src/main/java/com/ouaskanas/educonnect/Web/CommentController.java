package com.ouaskanas.educonnect.Web;

import com.ouaskanas.educonnect.Dao.Entities.Comment;
import com.ouaskanas.educonnect.Service.Manager.CommentManager;
import com.ouaskanas.educonnect.Service.Manager.PostManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/comment")
public class CommentController {

    private PostManager postManager;
    private CommentManager commentManager;

    @GetMapping("/{post_id}/allcomments")
    public List<Comment> getAllComments(@PathVariable long post_id) {
        return commentManager.getCommentFromPost(post_id);
    }

    @PostMapping("/{post_id}/addcomment")
    public Comment addComment(@PathVariable long post_id, @RequestBody Comment comment) {
        return commentManager.postComment(post_id, comment);
    }

    @DeleteMapping("/{post_id}/{comment_id}")
    public String deleteComment(@PathVariable long post_id, @PathVariable long comment_id) {
        commentManager.deleteComment(post_id, comment_id);
        return "Comment deleted";
    }
}
