package com.apekama.backend.repository;

import com.apekama.backend.model.Comment;
import com.apekama.backend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
