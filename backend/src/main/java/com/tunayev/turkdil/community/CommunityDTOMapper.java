package com.tunayev.turkdil.community;

import com.tunayev.turkdil.model.Community;
import com.tunayev.turkdil.model.User;
import com.tunayev.turkdil.model.UserCommunity;
import com.tunayev.turkdil.user.UserDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CommunityDTOMapper implements Function<Community, CommunityDTO> {
    @Override
    public CommunityDTO apply(Community community) {
        return new CommunityDTO(
                community.getId(),
                community.getName(),
                community.isPrivate(),
                community.getDescription(),
                community.getUsers(),
                community.getUserCommunities().stream()
                        .filter(userCommunity -> userCommunity.getRole().equals("ADMIN"))
                        .map(UserCommunity::getUser)
                        .findFirst()
                        .orElse(null),
                community.getUserCommunities().stream()
                        .filter(userCommunity -> userCommunity.getRole().equals("MODERATOR"))
                        .map(UserCommunity::getUser)
                        .collect(Collectors.toList()),
                community.getTemplates(),
                community.getPosts()
        );
    }

}
