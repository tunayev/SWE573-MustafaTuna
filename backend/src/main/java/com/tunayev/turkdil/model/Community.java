package com.tunayev.turkdil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tunayev.turkdil.post.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueName", columnNames = { "name" }) })
@Entity
public class Community {
    @Id
    @GeneratedValue
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String description;
    private boolean isPrivate;

    @JsonIgnore
    @ManyToMany(mappedBy = "communities")
    private List<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "community")
    private List<UserCommunity> userCommunities;

    @JsonIgnore
    @OneToMany(mappedBy = "community")
    private List<Post> posts;

/*    @JsonIgnore
    @OneToMany
    private List<Community> subCommunities;

    @JsonIgnore
    @ManyToOne
    private Community parentCommunity;*/

    @JsonIgnore
    @OneToMany
    private List<Template> templates;
}
