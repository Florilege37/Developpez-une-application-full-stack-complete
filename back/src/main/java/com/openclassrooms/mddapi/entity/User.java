package com.openclassrooms.mddapi.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NonNull
    @Size(max = 40)
    @Column(name="nickname")
    private String nickname;

    @NonNull
    @Size(max = 50)
    @Email
    @Column(name="email")
    private String email;

    @NonNull
    @Size(max = 120)
    @Column(name="password")
    private String password;

    /*@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;*/

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /*//Le "user" est le nom de l'attribut dans l'entity "POSTS"
    @OneToMany(mappedBy = "user")
    private Set<Post> posts;*/

}
