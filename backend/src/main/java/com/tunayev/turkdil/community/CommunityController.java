package com.tunayev.turkdil.community;

import com.tunayev.turkdil.model.Community;
import com.tunayev.turkdil.model.User;
import com.tunayev.turkdil.model.UserCommunity;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/communities")
public class CommunityController {
    private final CommunityService communityService;
    private Mapper mapper;

    @GetMapping
    public List<CommunityDTO> getAll() {
        return communityService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunityDTO> get(
            @PathVariable int id
    ) {
        return ResponseEntity.ok(communityService.get(id));
    }

    @PostMapping
    public ResponseEntity<Community> create(
            @RequestBody CommunityCreateRequest request
    ) {
        return ResponseEntity.ok(communityService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Community> update(
            @RequestBody CommunityUpdateRequest request
    ) {
        return ResponseEntity.ok(communityService.update(request.getId(), request));
    }

    @DeleteMapping("/{id}")
    public boolean delete(
            @RequestBody CommunityDeleteRequest request
    ) {
        return communityService.delete(request.getId());
    }

    @GetMapping("/search")
    public List<CommunityDTO> search(
            @RequestParam String query
    ) {
        return communityService.search(query);
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<CommunityDTO> join(
            @PathVariable int id,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(communityService.join(id, user));
    }

    @PostMapping("/{id}/leave")
    public boolean leave(
            @PathVariable int id,
            @AuthenticationPrincipal User user
    ) {
        return communityService.leave(id, user);
    }

    @PostMapping("/{id}/approve/{userId}")
    public ResponseEntity<CommunityDTO> approve(
            @PathVariable int id,
            @PathVariable int userId,
            @AuthenticationPrincipal User approver
    ) {
        return ResponseEntity.ok(communityService.approve(id, userId, approver));
    }

    @GetMapping("/{id}/getRole")
    public String getRole(
            @PathVariable int id,
            @AuthenticationPrincipal User user
    ) {
        // Get the role of the user in the community
        return communityService.getRole(id, user);
    }

    @GetMapping("/{id}/getUserCommunity")
    public ResponseEntity<UserCommunity> getUserCommunity(
            @PathVariable int id,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(communityService.getUserCommunity(id, user));
    }


}

