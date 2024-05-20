package com.tunayev.turkdil.user;

import com.tunayev.turkdil.model.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getAvatar(),
                user.getCommunities(),
                user.getPosts()
        );
    }
}
