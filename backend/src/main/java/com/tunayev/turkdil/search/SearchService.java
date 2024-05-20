package com.tunayev.turkdil.search;

import com.tunayev.turkdil.model.Community;
import com.tunayev.turkdil.model.User;
import com.tunayev.turkdil.post.Post;
import com.tunayev.turkdil.post.PostRepository;
import com.tunayev.turkdil.repository.CommunityRepository;
import com.tunayev.turkdil.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final PostRepository postRepository;

    public SearchDTO search(SearchRequest request) {
        List<User> users = userRepository.findByNicknameStartingWith("%" + request.getQuery() + "%");
        System.out.println(users);
        List<Community> communities = communityRepository.findByNameContaining(request.getQuery());
        List<Post> posts = postRepository.findByTitleContaining(request.getQuery());
        return new SearchDTO(users, posts, communities);
    }

    public List<User> searchUsers(SearchRequest request) {
        return userRepository.findByNicknameStartingWith(request.getQuery());
    }
}
