package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts, Long> {
}
