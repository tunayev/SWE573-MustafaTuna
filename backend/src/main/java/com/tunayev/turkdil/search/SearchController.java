package com.tunayev.turkdil.search;

import com.tunayev.turkdil.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/search")
public class SearchController {
    private final SearchService searchService;

    @GetMapping("")
    public SearchDTO search(SearchRequest request) {
        return searchService.search(request);
    }

    @GetMapping("/users")
    public List<User> searchUsers(SearchRequest request) {
        return searchService.searchUsers(request);
    }

}
