package com.tunayev.turkdil.repository;

import com.tunayev.turkdil.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
