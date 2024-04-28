package com.tunayev.turkdil.community;

import com.tunayev.turkdil.model.Community;
import com.tunayev.turkdil.user.UserDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CommunityDTOMapper implements Function<Community, CommunityDTO>{
    @Override
    public CommunityDTO apply(Community community) {
        return new CommunityDTO(
                community.getId(),
                community.getName(),
                community.getDescription(),
                community.getUsers().stream()
                        .map(user -> new UserDTO(
                                user.getId(),
                                user.getEmail(),
                                user.getNickname(),
                                user.getAvatar()
                                ))
                        .collect(Collectors.toList()
                        )
        );
    }

}
