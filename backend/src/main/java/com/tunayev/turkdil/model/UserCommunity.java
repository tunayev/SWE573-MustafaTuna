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
@Entity(name = "user_community")
public class UserCommunity {

    @EmbeddedId
    private UserCommunityKey userCommunityKey;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("communityId")
    @JoinColumn(name = "community_id")
    private Community community;

    String role;

    public String getRole(Community community, User user) {
        return this.role;
    }




}
