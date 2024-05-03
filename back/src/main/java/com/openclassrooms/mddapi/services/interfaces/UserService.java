package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.entity.User;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface UserService {

    /**
     * Récupère les détails d'un User grâce à son ID
     * @return
     */
    User findById(Long id);

    /**
     * Récupère les détails d'un User grâce à son email
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * Permet de mettre à jour le pseudo et/ou l'email de l'utilisateur
     * @param id
     * @param newUserDto
     * @return ResponseEntity
     */
    void updateUser(String id, UserDto newUserDto);
    /**
     * Permet de récupérer les information de l'utilisateur connecté
     * @return ResponseEntity
     */
    User getMe(Principal user);

    /**
     * Permet de récupérer les information de l'utilisateur ayant l'id
     * @param id
     * @return ResponseEntity
     */
    User getById(String id);

    User findByNickname(String nickname);

    void save(User user);

    boolean existByEmail(String email);
    boolean existByNickname(String nickname);

}
