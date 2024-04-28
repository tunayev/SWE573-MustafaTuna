package com.tunayev.turkdil.template;

import com.tunayev.turkdil.community.CommunityDTO;
import com.tunayev.turkdil.model.User;
import com.tunayev.turkdil.user.UserDTO;

public record TemplateDTO(
    String name,
    String fields,
    UserDTO userDTO,
    CommunityDTO communityDTO

) {
}
