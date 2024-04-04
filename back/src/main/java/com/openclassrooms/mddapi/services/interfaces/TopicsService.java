package com.openclassrooms.mddapi.services.interfaces;

import com.openclassrooms.mddapi.entity.Topics;

import java.util.List;

public interface TopicsService {

    List<Topics> getAllTopics();

    Topics findById(Long id);

    Topics findByTheme(String theme);

    void subscribe(Long id, Long userId);

    void unsubscribe(Long id, Long userId);
}
