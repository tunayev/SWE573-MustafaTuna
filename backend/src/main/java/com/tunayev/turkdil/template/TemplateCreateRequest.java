package com.tunayev.turkdil.template;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class TemplateCreateRequest {
    public String name;
    public String fields;
    public int userId;
    public int communityId;

    TemplateCreateRequest(
        String name,
        String fields,
        int userId,
        int communityId
    ) {
        this.name = name;
        this.fields = validateFields(fields);
        this.userId = userId;
        this.communityId = communityId;

    }

    public String validateFields(String fields) {
        if (fields == null) {
            throw new IllegalArgumentException("Fields cannot be null");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.readTree(fields);
        } catch (Exception e) {
            throw new IllegalArgumentException("Fields must be a valid JSON object");
        }
        return fields;
    }
}
