package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.Messages;
import com.openclassrooms.mddapi.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessagesRepository  extends JpaRepository<Messages, Long> {
    List<Messages> findByPostId(Long id);
}
