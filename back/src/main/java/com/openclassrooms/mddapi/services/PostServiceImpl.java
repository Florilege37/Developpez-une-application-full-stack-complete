package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.entity.Posts;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.services.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Posts> getPostsByTopicIds(List<Long> ids) {
        List<Posts> posts = new ArrayList<>();
        for (Long id : ids) {
            posts.addAll(postRepository.findAllByTopicId(id));
        }
        return posts;
    }
}
