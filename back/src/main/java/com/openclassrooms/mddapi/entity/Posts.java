package com.openclassrooms.mddapi.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Size(max = 40)
    @Column(name="title")
    private String title;

    @Size(max = 2000)
    @Column(name="description")
    private String description;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name="topic_id")
    private Long topic_id;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;

    /*@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="topic_id")
    private Topics topic;*/
}
