package com.tunayev.turkdil.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tunayev.turkdil.model.Community;
import com.tunayev.turkdil.model.Template;
import com.tunayev.turkdil.model.User;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue
    private int id;

    @NonNull
    private String title;

    @NonNull
    private String body;

    @ManyToOne
    private Community community;

    @ManyToOne
    private User user;

    @NonNull
    @JsonIgnore
    @ManyToOne
    private Template template;
}
