package com.tunayev.turkdil.component;

import com.tunayev.turkdil.model.User;
import com.tunayev.turkdil.user.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getAvatar(),
                user.getCommunities(),
                user.getPosts()
        );
    }

    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.id());
        user.setEmail(userDTO.email());
        user.setNickname(userDTO.nickname());
        user.setAvatar(userDTO.avatar());
        return user;
    }
}
