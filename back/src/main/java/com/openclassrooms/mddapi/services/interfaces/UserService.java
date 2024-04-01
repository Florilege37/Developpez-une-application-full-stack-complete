package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.entity.User;

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

    User findByNickname(String nickname);

    void save(User user);

    boolean existByEmail(String email);
    boolean existByNickname(String nickname);

}
