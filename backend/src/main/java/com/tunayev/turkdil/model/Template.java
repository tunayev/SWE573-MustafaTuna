package com.tunayev.turkdil.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;

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

    private Date createdDate;
}
