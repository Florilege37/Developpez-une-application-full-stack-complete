package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.entity.Posts;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

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
    Posts findById(Long id);

    /**
     * Permet de récupérer tous les posts des topics auxquel l'utilisateur est abonnés
     * @param ids des Topics auxquels l'utilisateur est abonné
     * @return Tous les posts qui doivent être affichés sur la page d'accueil
     */
    List<Posts> getPostsByTopicIds(List<Long> ids);

    List<Posts> getAllPosts(Principal user);
}
