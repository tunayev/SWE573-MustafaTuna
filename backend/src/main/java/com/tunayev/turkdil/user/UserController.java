package com.tunayev.turkdil.user;

import com.tunayev.turkdil.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserDTOMapper mapper;

    @GetMapping("/")
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("/me")
    public User getMe(
            @AuthenticationPrincipal User user
    ) {
        return user;
    }

    @GetMapping("/{id}")
    public UserDTO get(
            @PathVariable int id
    ) {
        return userService.get(id);
    }

    @PostMapping("/")
    public UserDTO create(
            @RequestBody UserCreateRequest request
    ) {
        return userService.create(request);
    }

    @PutMapping("/{id}")
    public UserDTO update(
            @RequestBody UserUpdateRequest request,
            @PathVariable int id
    ) {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public boolean delete(
            @PathVariable int id
    ) {
        return userService.delete(id);
    }

    @GetMapping("/search")
    public List<UserDTO> search(
            @RequestParam String query
    ) {
        return userService.search(query);
    }
}