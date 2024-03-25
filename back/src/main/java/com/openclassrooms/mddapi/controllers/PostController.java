package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.PostDto;
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

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto){
        Posts posts = postMapper.toEntity(postDto);
        postService.createPost(posts);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") String id){
        try {
            Posts posts = this.postService.getPost(Long.valueOf(id));

            if (posts==null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(postMapper.toDto(posts));
        }  catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
