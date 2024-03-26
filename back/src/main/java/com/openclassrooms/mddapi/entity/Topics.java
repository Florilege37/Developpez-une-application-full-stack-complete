package com.openclassrooms.mddapi.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Size(max = 40)
    @Column(name="theme")
    private String theme;

    @Size(max = 2000)
    @Column(name="description")
    private String description;
}
