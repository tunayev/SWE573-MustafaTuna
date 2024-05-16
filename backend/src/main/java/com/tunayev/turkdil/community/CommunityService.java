package com.tunayev.turkdil.community;

import com.tunayev.turkdil.model.Community;
import com.tunayev.turkdil.model.User;
import com.tunayev.turkdil.model.UserCommunity;
import com.tunayev.turkdil.repository.CommunityRepository;
import com.tunayev.turkdil.repository.UserCommunityRepository;
import com.tunayev.turkdil.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository repository;
    private final UserRepository userRepository;
    private final CommunityDTOMapper mapper;
    private final UserCommunityRepository userCommunityRepository;

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
        // TODO: Authorization - check if user is allowed to update this community
        Community community = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Community not found"));
        community.setName(request.getName());
        community.setDescription(request.getDescription());
        community.setPrivate(request.isPrivate());
        repository.save(community);
        return community;
    }

    public boolean delete(int id) {
        // TODO: Authorization - check if user is allowed to delete this community
        repository.deleteById(id);
        return true;
    }

    public List<CommunityDTO> search(String query) {
        return repository.findByNameContaining(query)
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    public CommunityDTO join(int id, User user) {
        // 1. Check if the community exists
        Community community = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Community not found"));
        // 2. Check if the user is already in the community
        boolean alreadyInCommunity = community.getUsers().stream()
                .anyMatch(u -> u.getId() == user.getId());
        if (alreadyInCommunity) {
            throw new IllegalArgumentException("User is already in the community");
        }

        // 3. Add the user to the community
        UserCommunity userCommunity = new UserCommunity();
        userCommunity.setCommunity(community);
        userCommunity.setUser(user);
        userCommunity.setPending(community.isPrivate());
        userCommunity.setRole("MEMBER");
        userCommunityRepository.save(userCommunity);
        System.out.println("User joined community");
        return mapper.apply(community);
    }

    public boolean leave(int id, User user) {
        // 1. Check if the community exists
        Community community = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Community not found"));
        // 2. Check if the user is in the community
        UserCommunity userCommunity = userCommunityRepository.findAll().stream()
                .filter(uc -> uc.getUser().getId() == user.getId() && uc.getCommunity().getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User is not in the community"));
        // 3. Remove the user from the community
        userCommunityRepository.delete(userCommunity);
        return true;
    }

    public CommunityDTO approve(int id, int userId, User approver) {
        // Get approver role in the community by looking at the UserCommunity relationship
        boolean hasRights = UserCommunity.builder()
                .user(approver)
                .community(repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Community not found")))
                .build()
                .getRole().equals("ADMIN");
        if (!hasRights) {
            throw new IllegalArgumentException("User does not have rights to approve users");
        }

        Community community = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Community not found"));
        User user = community.getUsers().stream()
                .filter(u -> u.getId() == userId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        UserCommunity userCommunity = UserCommunity.builder()
                .user(user)
                .community(community)
                .pending(false)
                .role("MEMBER")
                .build();
        //repository.save(community);
        return mapper.apply(community);
    }

    public String getRole(int communityId, User user) {
        // 1. Find the UserCommunity relationship
        UserCommunity userCommunity = userCommunityRepository.findAll().stream()
                .filter(uc -> uc.getUser().getId() == user.getId() && uc.getCommunity().getId() == communityId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User is not in the community"));
        return userCommunity.getRole();
    }


    public UserCommunity getUserCommunity(int communityId, User user) {
        // 1. Find the UserCommunity relationship
        UserCommunity userCommunity = userCommunityRepository.findAll().stream()
                .filter(uc -> uc.getUser().getId() == user.getId() && uc.getCommunity().getId() == communityId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User is not in the community"));
        return userCommunity;
    }

}
