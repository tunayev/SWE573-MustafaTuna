package com.tunayev.turkdil.community;

import com.tunayev.turkdil.model.Community;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.ok(communityService.update(1, request));
    }

    @DeleteMapping("/{id}")
    public boolean delete(
            @RequestBody CommunityDeleteRequest request
    ) {
        return communityService.delete(1);
    }


}

