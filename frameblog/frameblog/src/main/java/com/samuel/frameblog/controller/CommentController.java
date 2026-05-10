package com.samuel.frameblog.controller;

import com.samuel.frameblog.exception.ResourceNotFoundException;
import com.samuel.frameblog.model.Comment;
import com.samuel.frameblog.model.Post;
import com.samuel.frameblog.model.User;
import com.samuel.frameblog.repository.CommentRepository;
import com.samuel.frameblog.repository.PostRepository;
import com.samuel.frameblog.repository.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;

    public CommentController(CommentRepository commentRepository,
                             PostRepository postRepository,
                             UserRepository userRepository,
                             ApplicationEventPublisher eventPublisher) {

        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @PostMapping("/post/{postId}/user/{userId}")
    public Comment createComment(@PathVariable Long postId,
                                 @PathVariable Long userId,
                                 @RequestBody Comment comment) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        comment.setPost(post);
        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        eventPublisher.publishEvent(savedComment);

        return savedComment;
    }
}