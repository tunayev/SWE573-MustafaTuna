package com.tunayev.turkdil.repository;

import com.tunayev.turkdil.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateRepository extends JpaRepository<Template, Integer> {
    List<Template> findAllByCommunityId(int communityId);
}
