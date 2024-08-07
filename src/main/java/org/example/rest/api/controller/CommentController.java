package org.example.rest.api.controller;


import org.example.rest.api.entity.Comment;
import org.example.rest.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RequestMapping("/api/posts")
@ResponseBody
public class CommentController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CommentService commentService;

    @PostMapping("{postId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable("postId") Long postId,
                                                 @RequestBody Comment comment){
        Comment data = commentService.createComment(postId, comment);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }
    @PutMapping("/{postId}/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("postId") Long postId,
                                                 @PathVariable("id") Long id,
                                                  @RequestBody Comment comment) {
        Comment data = commentService.updateComment(postId, id, comment);
        return new ResponseEntity<>(data, HttpStatus.NO_CONTENT);
    }

    @GetMapping("{postId}/comments/{commentId}")
    public ResponseEntity<Comment> getCommentByPostId(@PathVariable("postId") Long postId,
                                                      @PathVariable("commentId") Long commentId) {
        Comment data = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("postId") Long postId, @PathVariable Long commentId) {
               commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
