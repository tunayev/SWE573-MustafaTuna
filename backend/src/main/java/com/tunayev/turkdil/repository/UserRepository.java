package com.tunayev.turkdil.repository;

import com.tunayev.turkdil.model.Community;
import com.tunayev.turkdil.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    List<User> findByNicknameStartingWith(String query);
    List<User> findByCommunities(Community community);
}
