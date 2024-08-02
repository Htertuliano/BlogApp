package org.example.service;

import org.example.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long postId, Comment post);
    List<Comment> getAllComment();
    Comment getCommentsByPostId(Long id);
    Comment updateComment(Long id, Comment post);
    void deleteComment(Long id);
}