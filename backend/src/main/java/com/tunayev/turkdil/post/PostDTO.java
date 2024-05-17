package com.tunayev.turkdil.post;

import com.tunayev.turkdil.model.Community;
import com.tunayev.turkdil.model.User;

import java.util.List;

public record PostDTO(
        int id,
        String title,
        String body,
        Community community,
        User user
) {
}
