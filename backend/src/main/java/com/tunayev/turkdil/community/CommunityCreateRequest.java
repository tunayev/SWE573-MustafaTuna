package com.tunayev.turkdil.community;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityCreateRequest {
    private String name;
    private String description;
    private boolean isPrivate;
}
