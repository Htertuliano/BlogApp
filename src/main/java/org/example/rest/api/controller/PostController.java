package org.example.rest.api.controller;


import org.example.rest.api.PostResponse;
import org.example.rest.api.entity.Post;
import org.example.rest.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/posts")
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post data = postService.createPost(post);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
        @RequestParam(value = "sortDirection", defaultValue = "asc", required = false) String sortDirection
        ){
        PostResponse data = postService.getAllPosts(pageNo, pageSize, sortBy,sortDirection);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") Long postId) {
        Post data = postService.getPostById(postId);
        if (data == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable("postId") Long postId,
                                            @RequestBody Post post) {
        Post data = postService.updatePost(postId, post);
        return new ResponseEntity<>(data, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>("post deleted successfully", HttpStatus.NO_CONTENT);
    }
}
