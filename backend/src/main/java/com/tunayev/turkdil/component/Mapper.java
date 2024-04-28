package com.tunayev.turkdil.component;

import com.tunayev.turkdil.model.User;
import com.tunayev.turkdil.user.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getAvatar()
        );
    }
}
