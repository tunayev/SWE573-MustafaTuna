package com.tunayev.turkdil.community;

import com.tunayev.turkdil.model.Template;
import com.tunayev.turkdil.model.User;
import com.tunayev.turkdil.post.Post;

import java.util.List;

public record CommunityDTO(
        int id,
        String name,
        boolean isPrivate,
        String description,
        List<User> users,
        User admin,
        List<User> moderators,
        List<Template> templates,
        List<Post> posts
) {
}
