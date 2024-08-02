package org.example.service;

import org.example.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long postId, Comment post);
    List<Comment> getAllComment();
    Comment getCommentsByPostId(Long postId);
    Comment updateComment(Long postId, Comment post);
    void deleteComment(Long postId, Comment comment);
    Comment getCommentById(Long postId, Long commentId);
}
