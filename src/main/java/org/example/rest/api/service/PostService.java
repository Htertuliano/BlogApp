package org.example.rest.api.service;






import org.example.rest.api.PostResponse;
import org.example.rest.api.entity.Post;

import java.util.List;

public interface PostService {
    Post createPost(Post post);
    PostResponse getAllPosts(int pageNo, int pageSze, String sortBy, String sortDirection);
    Post getPostById(Long id);
    Post updatePost(Long id, Post post);
    void deletePost(Long id);
}
