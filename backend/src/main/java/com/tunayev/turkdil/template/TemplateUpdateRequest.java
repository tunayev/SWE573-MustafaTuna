package com.tunayev.turkdil.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateUpdateRequest {
    private int id;
    private String name;
    private String fields;
    private int userId;
    private int communityId;
}
