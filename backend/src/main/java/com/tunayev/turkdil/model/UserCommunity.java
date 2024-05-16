package com.tunayev.turkdil.model;

import com.tunayev.turkdil.usercommunity.UserCommunityKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueNumberAndStatus", columnNames = { "user_id", "community_id", "role" }) })
@Entity(name = "user_community")
public class UserCommunity {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    String role;

    boolean pending;

    public String getRole(Community community, User user) {
        return this.role;
    }




}
