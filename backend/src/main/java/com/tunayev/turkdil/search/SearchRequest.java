package com.tunayev.turkdil.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
    private String query;
    private boolean advanced;
    private List<String> where;
}
