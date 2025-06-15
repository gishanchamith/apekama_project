package com.apekama.backend.controller;

import com.apekama.backend.model.Community;
import com.apekama.backend.repository.CommunityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/communities")
public class CommunityController {
    private final CommunityRepository repository;

    public CommunityController(CommunityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Community> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Community create(@RequestBody Community community) {
        return repository.save(community);
    }
}
