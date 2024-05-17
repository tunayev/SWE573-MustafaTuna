package com.tunayev.turkdil.post;

import com.tunayev.turkdil.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByTitleContaining(String query);
}
