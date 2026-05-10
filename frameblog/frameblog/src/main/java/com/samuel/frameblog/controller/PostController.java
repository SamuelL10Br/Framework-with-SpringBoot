package com.samuel.frameblog.controller;

import com.samuel.frameblog.exception.ResourceNotFoundException;
import com.samuel.frameblog.model.Post;
import com.samuel.frameblog.model.User;
import com.samuel.frameblog.repository.PostRepository;
import com.samuel.frameblog.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    @Cacheable("posts")
    public List<Post> getAllPosts() {
        System.out.println("Buscando posts no banco...");
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));
    }

    @PostMapping("/{userId}")
    @CacheEvict(value = "posts", allEntries = true)
    public Post createPost(@PathVariable Long userId, @RequestBody Post post) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        post.setUser(user);
        post.setCreatedAt(LocalDateTime.now());

        return postRepository.save(post);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "posts", allEntries = true)
    public void deletePost(@PathVariable Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));

        postRepository.delete(post);
    }
}