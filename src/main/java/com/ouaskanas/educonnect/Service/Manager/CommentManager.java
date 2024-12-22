package com.ouaskanas.educonnect.Service.Manager;

import com.ouaskanas.educonnect.Dao.Entities.Comment;

import java.util.List;

public interface CommentManager {
    public List<Comment> getCommentFromPost(int post_id);
    public Comment postComment(int post_id, Comment comment);
    public boolean deleteComment(int comment_id, int post_id);

}
