package com.tunayev.turkdil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tunayev.turkdil.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique=true)
    private String email;
    
    @JsonIgnore
    private String password;
    private String nickname;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_community",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "community_id")
    )
    private List<Community> communities;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserCommunity> userCommunities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isCommunityMember(Community community) {
        return communities.stream()
                .anyMatch(c -> c.getId() == community.getId());
    }

    public String getCommunityRole(Community community) {
        // TODO: Get the community role by looking at the UserCommunity relationship.
        return "admin";
    }

    public void addUserCommunity(UserCommunity userCommunity) {
        userCommunities.add(userCommunity);
    }
}
