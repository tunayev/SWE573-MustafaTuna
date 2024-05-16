package com.tunayev.turkdil.usercommunity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tunayev.turkdil.community.CommunityDTO;
import com.tunayev.turkdil.user.UserDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserCommunityDTO (
        UserDTO user,
        CommunityDTO community,
        String communityRole,
        boolean pending
) {
}
