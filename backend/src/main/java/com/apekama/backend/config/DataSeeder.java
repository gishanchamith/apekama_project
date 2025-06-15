package com.apekama.backend.config;

import com.apekama.backend.model.*;
import com.apekama.backend.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner initDatabase(CommunityRepository communityRepository,
                                   PostRepository postRepository,
                                   CommentRepository commentRepository) {
        return args -> {
            if (communityRepository.count() == 0) {
                Community general = communityRepository.save(new Community("General"));
                Community sports = communityRepository.save(new Community("Sports"));

                Post videoPost = postRepository.save(new Post(
                        "Welcome",
                        "Intro video",
                        "https://www.youtube.com/embed/dQw4w9WgXcQ",
                        PostType.VIDEO,
                        general));
                Post notePost = postRepository.save(new Post(
                        "Rules",
                        "Be nice and share knowledge.",
                        null,
                        PostType.NOTE,
                        general));

                commentRepository.save(new Comment("Great intro!", videoPost));
                commentRepository.save(new Comment("Thanks for the rules", notePost));

                postRepository.save(new Post(
                        "Sports schedule",
                        "Season opening soon",
                        null,
                        PostType.NOTE,
                        sports));
            }
        };
    }
}
