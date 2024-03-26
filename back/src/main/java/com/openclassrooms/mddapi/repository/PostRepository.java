package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> {

    /**
     * Permet de trouver tous les posts lié à un Topic
     * @param topicId
     * @return
     */
    List<Posts> findAllByTopicId(Long topicId);

}
