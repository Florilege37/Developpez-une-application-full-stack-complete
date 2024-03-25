package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.entity.Posts;
import com.openclassrooms.mddapi.entity.Topics;
import com.openclassrooms.mddapi.mappers.TopicsMapper;
import com.openclassrooms.mddapi.services.TopicsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicsServiceImpl topicsService;

    @Autowired
    private TopicsMapper topicsMapper;

    /**
     * Permet de récupérer la liste de tous les topics disponibles
     * @return
     */
    @GetMapping("/get")
    public ResponseEntity<?> getAllTopics(){
        List<Topics> topics = this.topicsService.getAllTopics();

        return ResponseEntity.ok().body(this.topicsMapper.toDto(topics));
    }
}
