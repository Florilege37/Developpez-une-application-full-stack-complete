package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TopicsRepository extends JpaRepository<Topics, Long> {
    Optional<Topics> findByTheme(String theme);

}
