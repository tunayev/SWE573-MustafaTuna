package com.tunayev.turkdil.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDTO(
        int id,
        String email,
        String nickname,
        String avatar
) {
}