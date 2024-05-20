package com.tunayev.turkdil.search;

import com.tunayev.turkdil.model.Community;
import com.tunayev.turkdil.model.Template;
import com.tunayev.turkdil.model.User;
import com.tunayev.turkdil.post.Post;

import java.util.List;

public record SearchDTO(
        List<User> users,
        List<Post> posts,
        List<Community> communities
) {
}
