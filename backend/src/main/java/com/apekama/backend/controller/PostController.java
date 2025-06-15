package com.apekama.backend.controller;

import com.apekama.backend.model.Community;
import com.apekama.backend.model.Post;
import com.apekama.backend.model.PostType;
import com.apekama.backend.repository.CommunityRepository;
import com.apekama.backend.repository.PostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PostController {
    private final CommunityRepository communityRepository;
    private final PostRepository postRepository;

    public PostController(CommunityRepository communityRepository, PostRepository postRepository) {
        this.communityRepository = communityRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/posts/search")
    public List<Post> searchPosts(@RequestParam("q") String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }
        return postRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(query, query);
    }

    @GetMapping("/communities/{communityId}/posts")
    public List<Post> getPosts(@PathVariable Long communityId) {
        Optional<Community> community = communityRepository.findById(communityId);
        return community.map(postRepository::findByCommunity).orElse(List.of());
    }

    @PostMapping("/communities/{communityId}/posts")
    public Post createPost(@PathVariable Long communityId, @RequestBody Post post) {
        Community community = communityRepository.findById(communityId).orElseThrow();
        post.setCommunity(community);
        if (post.getType() == null) {
            post.setType(post.getVideoUrl() != null ? PostType.VIDEO : PostType.NOTE);
        }
        return postRepository.save(post);
    }
}
