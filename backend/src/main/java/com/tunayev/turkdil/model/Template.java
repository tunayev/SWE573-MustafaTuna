package com.tunayev.turkdil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tunayev.turkdil.post.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Template {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String fields;

    @NonNull
    @ManyToOne
    private User user;

    @ManyToOne
    private Community community;

    @JsonIgnore
    @OneToMany(mappedBy = "template")
    private List<Post> posts;

    private Date createdDate;

}
