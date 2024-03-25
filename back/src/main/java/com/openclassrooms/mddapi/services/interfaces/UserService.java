package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.entity.User;

public interface UserService {

    /**
     * Récupère les détails d'un User grâce à son ID
     * @return
     */
    User getUser(Long id);

    /**
     * Récupère les détails d'un User grâce à son email
     * @param email
     * @return
     */
    User findByEmail(String email);
}
