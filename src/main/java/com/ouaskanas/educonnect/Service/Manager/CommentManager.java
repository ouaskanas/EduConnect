package com.ouaskanas.educonnect.Service.Manager;

import com.ouaskanas.educonnect.Dao.Entities.Comment;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dto.CommentDto;

import java.util.List;

public interface CommentManager {
    public List<CommentDto> getCommentFromPost(int post_id);
    public CommentDto postComment(int post_id, String commenttxt);
    public boolean deleteComment(int comment_id, int post_id);

}
