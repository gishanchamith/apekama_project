package com.apekama.backend.model;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    private String videoUrl;

    @Enumerated(EnumType.STRING)
    private PostType type;

    @ManyToOne
    private Community community;

    public Post() {}

    public Post(String title, String content, String videoUrl, PostType type, Community community) {
        this.title = title;
        this.content = content;
        this.videoUrl = videoUrl;
        this.type = type;
        this.community = community;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }
    public PostType getType() { return type; }
    public void setType(PostType type) { this.type = type; }
    public Community getCommunity() { return community; }
    public void setCommunity(Community community) { this.community = community; }
}
