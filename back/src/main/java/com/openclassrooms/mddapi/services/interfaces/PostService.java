package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.entity.Posts;

public interface PostService {

    /**
     * Créer un nouveau "Posts" et l'ajoute dans la base
     * @param posts
     */
    void createPost(Posts posts);

    /**
     * Récupérer les données d'un "Posts" grâce à son ID
     * @param id
     * @return
     */
    Posts getPost(Long id);
}
