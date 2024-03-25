package com.openclassrooms.mddapi.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //Le "user" est le nom de l'attribut dans l'entity "POSTS"
    /*@OneToMany(mappedBy = "user")
    private List<Posts> posts;*/

}
