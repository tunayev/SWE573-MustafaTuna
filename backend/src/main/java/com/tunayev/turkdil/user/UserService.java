package com.tunayev.turkdil.user;

import com.tunayev.turkdil.model.User;
import com.tunayev.turkdil.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserDTOMapper mapper;

    public List<UserDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    public UserDTO get(int id) {
        return repository.findById(id)
                .map(mapper)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public UserDTO create(UserCreateRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .avatar(request.getAvatar())
                .build();
        repository.save(user);
        return mapper.apply(user);
    }

    public UserDTO update(int id, UserUpdateRequest request) {
        User user = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setEmail(request.getEmail());
        user.setNickname(request.getNickname());
        user.setAvatar(request.getAvatar());
        repository.save(user);
        return mapper.apply(user);
    }

    public boolean delete(int id) {
        repository.deleteById(id);
        return true;
    }

    public List<UserDTO> search(String query) {
        return repository.findByNicknameStartingWith(query)
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
