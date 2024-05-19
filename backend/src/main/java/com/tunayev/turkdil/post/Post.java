package com.tunayev.turkdil.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tunayev.turkdil.model.Community;
import com.tunayev.turkdil.model.Template;
import com.tunayev.turkdil.model.User;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

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
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private String body;

    @ManyToOne
    private Community community;

    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToOne
    private Template template;
}
