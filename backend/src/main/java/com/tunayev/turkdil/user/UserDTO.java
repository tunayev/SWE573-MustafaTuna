package com.tunayev.turkdil.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tunayev.turkdil.community.CommunityDTO;
import com.tunayev.turkdil.model.Community;

import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDTO(
        int id,
        String email,
        String nickname,
        String avatar,
        List<Community> communities
) {
}