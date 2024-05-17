package com.tunayev.turkdil.post;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("/{communityId}/all")
    public List<PostDTO> getAll(@PathVariable int communityId) {
        return postService.getAll(communityId);
    }

    @GetMapping("/{id}")
    public PostDTO get(@PathVariable int id) {
        return postService.get(id);
    }

    @PostMapping
    public Post create(@RequestBody PostCreateRequest request) {
        return postService.create(request);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable int id, @RequestBody PostUpdateRequest request) {
        return postService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        postService.delete(id);
    }


}

