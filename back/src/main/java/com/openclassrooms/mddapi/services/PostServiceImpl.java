package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.entity.Posts;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.services.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Override
    public void createPost(Posts posts) {
        postRepository.save(posts);
    }

    @Override
    public Posts findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
}
