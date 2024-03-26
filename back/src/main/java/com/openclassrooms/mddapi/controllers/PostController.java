package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.PostsDto;
import com.openclassrooms.mddapi.entity.Posts;
import com.openclassrooms.mddapi.mappers.PostMapper;
import com.openclassrooms.mddapi.services.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private PostMapper postMapper;

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

            System.out.println("FLORIAN :" + posts.getMessages());

            PostsDto postsDto = postMapper.toDto(posts);
            System.out.println("FLORIAN :" + postsDto.getMessage());

            if (posts==null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(postMapper.toDto(posts));
        }  catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
