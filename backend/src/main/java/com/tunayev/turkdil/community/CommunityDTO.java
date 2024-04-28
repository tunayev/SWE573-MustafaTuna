package com.tunayev.turkdil.community;

import com.tunayev.turkdil.user.UserDTO;

import java.util.List;

public record CommunityDTO(
        int id,
        String name,
        String description,
        List<UserDTO> users
) {
}
