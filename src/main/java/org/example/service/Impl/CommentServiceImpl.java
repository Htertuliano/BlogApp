package org.example.service.Impl;

import org.example.entity.Comment;
import org.example.entity.Post;
import org.example.repository.CommentRepository;
import org.example.repository.PostRepository;
import org.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;


    @Override
    public Comment createComment(Long postId, Comment comment) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new RuntimeException("Post with the id not found"));

                comment.setPost(post);

                return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getCommentsByPostId(Long postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new RuntimeException("Post with the id not found"));

        return commentRepository.getById(postId);
    }

    @Override
    public Comment updateComment(Long postId, Comment newComment) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new RuntimeException("Post with the id not found"));

            newComment.setPost(post);
            return commentRepository.save(newComment);
    }

    @Override
    public void deleteComment(Long postId, Comment comment) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new RuntimeException("Post with the id not found"));
        commentRepository.delete(comment);

    }
}
