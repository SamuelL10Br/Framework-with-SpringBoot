package com.samuel.frameblog.repository;

import com.samuel.frameblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}