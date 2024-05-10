package com.tunayev.turkdil.repository;

import com.tunayev.turkdil.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Integer> {
    List<Community> findByNameContaining(String query);
    List<Community> findCommunitiesByUsersId(int userId);
}
