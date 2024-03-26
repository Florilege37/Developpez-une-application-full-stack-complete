package com.openclassrooms.mddapi.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "post_id")
    private Long postId;

    @Size(max = 2000)
    @Column(name="message")
    private String message;
}
