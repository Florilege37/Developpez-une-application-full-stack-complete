package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.entity.Posts;
import com.openclassrooms.mddapi.entity.Topics;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.mappers.PostMapper;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.services.interfaces.PostService;
import com.openclassrooms.mddapi.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostMapper postMapper;
    @Override
    public void createPost(Posts posts) {
        postRepository.save(posts);
    }

    @Override
    public Posts findById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
    @Override
    public List<Posts> getPostsByTopicIds(List<Long> ids) {
        List<Posts> posts = new ArrayList<>();
        for (Long id : ids) {
            posts.addAll(postRepository.findAllByTopicId(id));
        }
        return posts;
    }

    @Override
    public ResponseEntity<?> getAllPosts(Principal user){
        if (user == null){
            return ResponseEntity.badRequest().build();
        }

        // On récupère le user
        String userMail = user.getName();
        User userResult = userService.findByEmail(userMail);

        // On récupère ses topics d'abonnement
        List<Topics> topics = userResult.getTopicSubscribed();
        List<Long> ids = topics.stream().map(Topics::getId).collect(Collectors.toList());

        //Pour chaque Topics, on récupère ses articles associés
        List<Posts> posts = getPostsByTopicIds(ids);

        Comparator<Posts> comparator = Comparator.comparing(Posts::getCreated_at).reversed();

        // Trier la liste de posts en utilisant le comparateur personnalisé
        Collections.sort(posts, comparator);

        return ResponseEntity.ok().body(postMapper.toDto(posts));
    }
}
