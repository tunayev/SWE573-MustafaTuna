package com.tunayev.turkdil.community;

import com.tunayev.turkdil.model.Community;
import com.tunayev.turkdil.repository.CommunityRepository;
import com.tunayev.turkdil.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository repository;
    private final CommunityDTOMapper mapper;

    public List<CommunityDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    public CommunityDTO get(int id) {
        return repository.findById(id)
                .map(mapper)
                .orElseThrow(() -> new IllegalArgumentException("Community not found"));
    }

    public Community create(CommunityCreateRequest request) {
        Community community = Community.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        repository.save(community);
        return community;
    }

    public Community update(int id, CommunityUpdateRequest request) {
        Community community = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Community not found"));
        community.setName(request.getName());
        community.setDescription(request.getDescription());
        repository.save(community);
        return community;
    }

    public boolean delete(int id) {
        repository.deleteById(id);
        return true;
    }

}
