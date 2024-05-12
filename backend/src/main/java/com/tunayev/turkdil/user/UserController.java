package com.tunayev.turkdil.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO get(int id) {
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
