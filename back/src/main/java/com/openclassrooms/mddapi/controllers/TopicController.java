package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.entity.Posts;
import com.openclassrooms.mddapi.entity.Topics;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.mappers.TopicsMapper;
import com.openclassrooms.mddapi.services.TopicsServiceImpl;
import com.openclassrooms.mddapi.services.interfaces.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    private TopicsService topicsService;

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

    @PostMapping("{userId}/subs/{id}")
    public ResponseEntity<?> subscribe(@PathVariable("id") String id, @PathVariable("userId") String userId){
        try {
            this.topicsService.subscribe(Long.parseLong(id), Long.parseLong(userId));

            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{userId}/subs/{id}")
    public ResponseEntity<?> unsubscribe(@PathVariable("id") String id, @PathVariable("userId") String userId){
        try {
            this.topicsService.unsubscribe(Long.parseLong(id), Long.parseLong(userId));

            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id){
        Topics topics = topicsService.findById(Long.valueOf(id));
        if (topics == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(topicsMapper.toDto(topics));
    }

    @GetMapping("/getTheme/{theme}")
    public ResponseEntity<?> getByTheme(@PathVariable("theme") String theme){
        Topics topics = topicsService.findByTheme(theme);
        if (topics == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(topicsMapper.toDto(topics));
    }


}
