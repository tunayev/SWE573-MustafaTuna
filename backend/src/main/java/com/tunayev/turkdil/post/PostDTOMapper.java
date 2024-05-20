package com.tunayev.turkdil.post;

import com.tunayev.turkdil.community.CommunityDTO;
import com.tunayev.turkdil.community.CommunityDTOMapper;
import com.tunayev.turkdil.model.Community;
import com.tunayev.turkdil.model.UserCommunity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;
@AllArgsConstructor
@Component
public class PostDTOMapper implements Function<Post, PostDTO> {
    private final CommunityDTOMapper communityDTOMapper;

    @Override
    public PostDTO apply(Post post) {
        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                post.getCommunity(),
                post.getUser()
        );
    }

}
