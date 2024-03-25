package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.Topics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicsRepository extends JpaRepository<Topics, Long> {
    Optional<Topics> findByTheme(String theme);

}
