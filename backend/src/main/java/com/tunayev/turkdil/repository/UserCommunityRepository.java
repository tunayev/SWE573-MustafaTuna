package com.tunayev.turkdil.repository;

import com.tunayev.turkdil.model.UserCommunity;
import com.tunayev.turkdil.usercommunity.UserCommunityKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommunityRepository extends JpaRepository<UserCommunity, Integer>{

}
