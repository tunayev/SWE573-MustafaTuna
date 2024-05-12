package com.tunayev.turkdil.community;

import com.tunayev.turkdil.model.Community;
import com.tunayev.turkdil.model.User;
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
    public ResponseEntity<CommunityDTO> leave(
            @PathVariable int id
    ) {
        return ResponseEntity.ok(communityService.leave(id));
    }

    @PostMapping("/{id}/approve/{userId}")
    public ResponseEntity<CommunityDTO> approve(
            @PathVariable int id,
            @PathVariable int userId
    ) {
        return ResponseEntity.ok(communityService.approve(id, userId));
    }


}

