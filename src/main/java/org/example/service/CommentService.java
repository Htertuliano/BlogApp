package org.example.service;

import org.example.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long postId, Comment post);
    List<Comment> getCommentsByPostId(Long postId);
    Comment updateComment(Long postId, Long commentId, Comment updateComment);
    void deleteComment(Long postId, Long comment);
    Comment getCommentById(Long postId, Long commentId);
}
