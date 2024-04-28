package com.tunayev.turkdil.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Field {
    @Id
    @GeneratedValue
    private int id;

    @NonNull
    private String type;
}
