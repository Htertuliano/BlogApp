package org.example.controller;

import org.example.entity.Comment;
import org.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api")
@ResponseBody
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("{postId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable("postId") Long postId, @RequestBody Comment comment){
        Comment data = commentService.createComment(postId, comment);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping("{postId}/comments/{commentId}")
    public ResponseEntity<Comment> getCommentByPostId(@PathVariable("postId") Long postId,
                                                      @PathVariable("commentId") Long commentId) {
        Comment data = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("postId") Long postId, @RequestBody Comment comment) {
               commentService.deleteComment(postId, comment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
