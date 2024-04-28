package com.tunayev.turkdil.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Community {
    @Id
    private int id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "communities")
    private List<User> users;


}
