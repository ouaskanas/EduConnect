package com.ouaskanas.educonnect.Service.Manager;

import com.ouaskanas.educonnect.Dao.Entities.Comment;

import java.util.List;

public interface CommentManager {
    public List<Comment> getCommentFromPost(long post_id);
    public Comment postComment(long post_id, Comment comment);
    public boolean deleteComment(long comment_id, long post_id);

}
