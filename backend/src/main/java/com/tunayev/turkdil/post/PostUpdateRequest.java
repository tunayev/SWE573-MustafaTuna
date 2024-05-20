package com.tunayev.turkdil.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequest {
    private int id;
    private String title;
    private String body;
    private int communityId;
    private List<String> postFields;
}
