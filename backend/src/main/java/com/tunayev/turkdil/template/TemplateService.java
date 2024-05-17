package com.tunayev.turkdil.template;

import com.tunayev.turkdil.model.Community;
import com.tunayev.turkdil.model.Template;
import com.tunayev.turkdil.model.User;
import com.tunayev.turkdil.repository.CommunityRepository;
import com.tunayev.turkdil.repository.TemplateRepository;
import com.tunayev.turkdil.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final TemplateDTOMapper TemplateDTOMapper;

    public List<TemplateDTO> getAll() {
        return templateRepository.findAll()
                .stream()
                .map(TemplateDTOMapper)
                .collect(Collectors.toList());
    }

    public TemplateDTO get(int id) {
        return templateRepository.findById(id)
                .map(TemplateDTOMapper)
                .orElseThrow(() -> new IllegalArgumentException("Template not found"));
    }

    public Template save(TemplateCreateRequest request, User user) {
        // TODO: Authorization - check if user is allowed to create a template
        // TODO: Validation - check if the fields are valid
        Community community = communityRepository.findById(request.getCommunityId())
                .orElseThrow(() -> new IllegalArgumentException("Community not found"));

        Template template = new Template();
        template.setName(request.getName());
        template.setFields(request.getFields());
        template.setUser(user);
        template.setCommunity(community);
        return templateRepository.save(template);
        /*TemplateDTO templateDTO = TemplateDTOMapper.apply(template);
        return templateDTO;*/
    }

    public TemplateDTO update(int id, TemplateUpdateRequest templateUpdateRequest) {
        // TODO: Authorization - check if user is allowed to update this template
        TemplateDTO templateDTO = get(id);
        Template template = templateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Template not found"));
        User user = userRepository.findById(templateDTO.userDTO().id())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Community community = communityRepository.findById(templateDTO.communityDTO().id())
                .orElseThrow(() -> new IllegalArgumentException("Community not found"));
        template.setName(templateDTO.name());
        template.setFields(templateDTO.fields());
        template.setUser(user);
        template.setCommunity(community);
        templateRepository.save(template);
        return TemplateDTOMapper.apply(template);
    }

    public boolean delete(int id) {
        // TODO: Authorization - check if user is allowed to delete this template
        templateRepository.deleteById(id);
        return true;
    }

}
