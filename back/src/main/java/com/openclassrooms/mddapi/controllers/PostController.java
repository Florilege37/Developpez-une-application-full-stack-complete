package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.PostsDto;
import com.openclassrooms.mddapi.entity.Posts;
import com.openclassrooms.mddapi.entity.Topics;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.mappers.PostMapper;
import com.openclassrooms.mddapi.services.PostServiceImpl;
import com.openclassrooms.mddapi.services.interfaces.PostService;
import com.openclassrooms.mddapi.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    /**
     * Permet de créer un nouveau "Posts"
     * @param postsDto
     * @return
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void createPost(@RequestBody PostsDto postsDto){
        Posts posts = postMapper.toEntity(postsDto);
        postService.createPost(posts);
    }

    /**
     * Permet de récupérer un "Posts" afin de l'afficher en détail
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public PostsDto getPost(@PathVariable("id") String id){
        try {
            Posts posts = this.postService.findById(Long.valueOf(id));

            if (posts==null){
                throw new BadRequestException();
            }

            return postMapper.toDto(posts);
        }  catch (NumberFormatException e) {
            throw new BadRequestException();
        }
    }

    /**
     * Permet de récupérer tous les articles que le User va voir sur sa page "Articles"
     * C'est à dire tous les articles des thèmes auxquels il est abonné.
     * @param user
     * @return
     */
    @GetMapping("/me/posts")
    public List<PostsDto> getAllPosts(Principal user){
        List<Posts> list =  this.postService.getAllPosts(user);
        return postMapper.toDto(list);
    }
}
