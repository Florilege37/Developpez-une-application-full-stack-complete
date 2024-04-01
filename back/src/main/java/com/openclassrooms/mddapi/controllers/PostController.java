package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.PostsDto;
import com.openclassrooms.mddapi.entity.Posts;
import com.openclassrooms.mddapi.entity.Topics;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.mappers.PostMapper;
import com.openclassrooms.mddapi.services.PostServiceImpl;
import com.openclassrooms.mddapi.services.interfaces.PostService;
import com.openclassrooms.mddapi.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserService userService;

    /**
     * Permet de créer un nouveau "Posts"
     * @param postsDto
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostsDto postsDto){
        Posts posts = postMapper.toEntity(postsDto);
        postService.createPost(posts);

        return ResponseEntity.ok().build();
    }

    /**
     * Permet de récupérer un "Posts" afin de l'afficher en détail
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") String id){
        try {
            Posts posts = this.postService.findById(Long.valueOf(id));

            if (posts==null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(postMapper.toDto(posts));
        }  catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Permet de récupérer tous les articles que le User va voir sur sa page "Articles"
     * C'est à dire tous les articles des thèmes auxquels il est abonné.
     * @param user
     * @return
     */
    @GetMapping("/me/posts")
    public ResponseEntity<?> getAllPosts(Principal user){
        if (user == null){
            return ResponseEntity.notFound().build();
        }

        // On récupère le user
        String userMail = user.getName();
        User userResult = userService.findByEmail(userMail);

        // On récupère ses topics d'abonnement
        List<Topics> topics = userResult.getTopicSubscribed();
        List<Long> ids = topics.stream().map(Topics::getId).collect(Collectors.toList());

        //Pour chaque Topics, on récupère ses articles associés
        List<Posts> posts = postService.getPostsByTopicIds(ids);

        return ResponseEntity.ok().body(postMapper.toDto(posts));
    }
}
