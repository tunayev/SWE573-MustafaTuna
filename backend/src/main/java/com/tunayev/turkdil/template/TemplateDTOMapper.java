package com.tunayev.turkdil.template;

import com.tunayev.turkdil.community.CommunityDTOMapper;
import com.tunayev.turkdil.model.Template;
import com.tunayev.turkdil.user.UserDTOMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TemplateDTOMapper implements Function<Template, TemplateDTO> {
    @Override
    public TemplateDTO apply(Template template) {
        return new TemplateDTO(
                template.getName(),
                template.getFields(),
                new UserDTOMapper().apply(template.getUser()),
                new CommunityDTOMapper().apply(template.getCommunity())
        );
    }
}
