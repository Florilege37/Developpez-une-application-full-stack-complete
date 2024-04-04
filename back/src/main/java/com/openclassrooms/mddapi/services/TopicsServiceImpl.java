package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.entity.Topics;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.repository.TopicsRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.services.interfaces.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicsServiceImpl implements TopicsService {

    @Autowired
    private TopicsRepository topicsRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Topics> getAllTopics() {
        return topicsRepository.findAll();
    }

    @Override
    public Topics findById(Long id) {
        return topicsRepository.findById(id).orElse(null);
    }

    @Override
    public Topics findByTheme(String theme) {
        return topicsRepository.findByTheme(theme).orElse(null);
    }

    @Override
    public void subscribe(Long id, Long userId) {
        Topics topics = topicsRepository.findById(id).orElse(null);
        User user = this.userRepository.findById(userId).orElse(null);

        if (topics == null || user == null) {
            throw new NotFoundException();
        }

        boolean alreadySubscribed = user.getTopicSubscribed().stream().anyMatch(o -> o.getId().equals(id));
        if(alreadySubscribed) {
            throw new BadRequestException();
        }

        user.getTopicSubscribed().add(topics);

        this.userRepository.save(user);
    }

    @Override
    public void unsubscribe(Long id, Long userId) {
        Topics topics = topicsRepository.findById(id).orElse(null);
        User user = this.userRepository.findById(userId).orElse(null);

        if (topics == null || user == null) {
            throw new NotFoundException();
        }

        boolean alreadySubscribed = user.getTopicSubscribed().stream().anyMatch(o -> o.getId().equals(id));
        if(!alreadySubscribed) {
            throw new BadRequestException();
        }

        user.setTopicSubscribed(user.getTopicSubscribed().stream().filter(topic -> !topic.getId().equals(id)).collect(Collectors.toList()));

        this.userRepository.save(user);
    }
}
