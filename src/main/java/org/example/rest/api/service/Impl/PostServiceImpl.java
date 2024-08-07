package org.example.rest.api.service.Impl;

import org.example.rest.api.PostResponse;
import org.example.rest.api.entity.Post;
import org.example.rest.api.repository.PostRepository;
import org.example.rest.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    Page<Post> posts = postRepository.findAll(pageable);
    List<Post> postList = posts.getContent();
    PostResponse postResponse = new PostResponse();
    postResponse.setContent(postList);
    postResponse.setPageNo(posts.getNumber());
    postResponse.setPageSize(posts.getSize());
    postResponse.setTotalPages(posts.getTotalPages());
    postResponse.setTotalElements(posts.getTotalElements());
    postResponse.setLast(posts.isLast());
        return postResponse;
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
