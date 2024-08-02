package org.example.service.Impl;

import org.example.entity.Post;
import org.example.repository.PostRepository;
import org.example.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long postId) {
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        return post;
    }

    @Override
    public Post updatePost(Long postId, Post updatePost) {
        // get post by id from database
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        // get update data
        post.setTitle(updatePost.getTitle());
        post.setContent(updatePost.getContent());
        post.setDescription(updatePost.getDescription());
        // save updated post
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        // get post by id from database
        Post post = postRepository
                .findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
        // delete
        postRepository.deleteById(postId);
    }
}
