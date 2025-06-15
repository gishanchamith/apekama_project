package com.apekama.backend.model;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String text;

    @ManyToOne
    private Post post;

    public Comment() {}

    public Comment(String text, Post post) {
        this.text = text;
        this.post = post;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }
}
