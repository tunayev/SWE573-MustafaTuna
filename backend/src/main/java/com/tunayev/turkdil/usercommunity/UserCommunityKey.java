package com.tunayev.turkdil.usercommunity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserCommunityKey implements Serializable {
    @Column(name = "user_id")
    private int userId;

    @Column(name = "community_id")
    private int communityId;

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCommunityKey)) return false;
        UserCommunityKey that = (UserCommunityKey) o;
        return userId == that.userId && communityId == that.communityId;
    }

    @Override
    public int hashCode() {
        // TODO: Implement hashCode()
        return 0;
    }*/

}
