package com.tunayev.turkdil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private boolean isPrivate;

    @JsonIgnore
    @ManyToMany(mappedBy = "communities")
    private List<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "community")
    private List<UserCommunity> userCommunities;

    public void addUserCommunity(UserCommunity userCommunity) {
        userCommunities.add(userCommunity);
    }

}
