package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.entity.Topics;
import com.openclassrooms.mddapi.repository.TopicsRepository;
import com.openclassrooms.mddapi.services.interfaces.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicsServiceImpl implements TopicsService {

    @Autowired
    private TopicsRepository topicsRepository;

    @Override
    public List<Topics> getAllTopics() {
        return topicsRepository.findAll();
    }
}
