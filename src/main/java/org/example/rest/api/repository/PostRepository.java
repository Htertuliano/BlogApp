package org.example.rest.api.repository;


import org.example.rest.api.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{

}
