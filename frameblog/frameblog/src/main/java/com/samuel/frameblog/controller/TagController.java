package com.samuel.frameblog.controller;

import com.samuel.frameblog.exception.ResourceNotFoundException;
import com.samuel.frameblog.model.Post;
import com.samuel.frameblog.model.Tag;
import com.samuel.frameblog.repository.PostRepository;
import com.samuel.frameblog.repository.TagRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagRepository tagRepository;
    private final PostRepository postRepository;

    public TagController(TagRepository tagRepository,
                         PostRepository postRepository) {

        this.tagRepository = tagRepository;
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagRepository.save(tag);
    }

    @PostMapping("/{tagId}/post/{postId}")
    public Post addTagToPost(@PathVariable Long tagId,
                             @PathVariable Long postId) {

        Tag tag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourceNotFoundException("Tag não encontrada"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));

        post.getTags().add(tag);

        return postRepository.save(post);
    }
}