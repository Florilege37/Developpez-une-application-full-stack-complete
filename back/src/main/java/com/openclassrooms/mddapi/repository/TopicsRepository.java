package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicsRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByTheme(String theme);

}
