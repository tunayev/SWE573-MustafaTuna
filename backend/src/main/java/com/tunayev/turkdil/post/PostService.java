package com.tunayev.turkdil.post;

import com.tunayev.turkdil.community.CommunityDTOMapper;
import com.tunayev.turkdil.model.Community;
import com.tunayev.turkdil.model.User;
import com.tunayev.turkdil.repository.CommunityRepository;
import com.tunayev.turkdil.repository.TemplateRepository;
import com.tunayev.turkdil.repository.UserCommunityRepository;
import com.tunayev.turkdil.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommunityDTOMapper communityDTOMapper;
    private final PostDTOMapper mapper;
    private final UserCommunityRepository userCommunityRepository;
    private final TemplateRepository templateRepository;

    public List<PostDTO> getAll(int communityId) {
        // Find posts by community id
        return communityRepository.findById(communityId)
                .map(Community::getPosts)
                .orElseThrow(() -> new IllegalArgumentException("Community not found"))
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    public PostDTO get(int id) {
        // Find post by id
        return postRepository.findById(id)
                .map(mapper)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    public Post create(PostCreateRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Create post
        var post = new Post();
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        post.setCommunity(communityRepository.findById(request.getCommunityId())
                .orElseThrow(() -> new IllegalArgumentException("Community not found")));
        post.setTemplate(templateRepository.findById(request.getTemplateId())
                .orElseThrow(() -> new IllegalArgumentException("Template not found")));
        post.setUser(user);
        return postRepository.save(post);
    }

    public Post update(int id, PostUpdateRequest request) {
        // Update post
        var post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        post.setTitle(request.getTitle());
        post.setBody(request.getBody());
        post.setCommunity(communityRepository.findById(request.getCommunityId())
                .orElseThrow(() -> new IllegalArgumentException("Community not found")));
        post.setBody(request.getBody());
        return postRepository.save(post);
    }

    public void delete(int id) {
        // Delete post
        postRepository.deleteById(id);
    }
}
