package org.example.controller;

import org.example.entity.Comment;
import org.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api")
@ResponseBody
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("{postId}/Comments")
    public ResponseEntity<Comment> createComment(@PathVariable("postId") Long postId, @RequestBody Comment comment){
        Comment data = commentService.createComment(postId, comment);
        return new ResponseEntity<>(data, HttpStatus.CREATED)
    }


}
