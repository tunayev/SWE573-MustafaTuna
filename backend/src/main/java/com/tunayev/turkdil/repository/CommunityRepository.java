package com.tunayev.turkdil.repository;

import com.tunayev.turkdil.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Integer> {
}
