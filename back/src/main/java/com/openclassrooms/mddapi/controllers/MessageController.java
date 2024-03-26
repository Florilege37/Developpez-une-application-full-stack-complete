package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.MessageDto;
import com.openclassrooms.mddapi.entity.Messages;
import com.openclassrooms.mddapi.entity.Posts;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.mappers.MessageMapper;
import com.openclassrooms.mddapi.services.MessagesServiceImpl;
import com.openclassrooms.mddapi.services.PostServiceImpl;
import com.openclassrooms.mddapi.services.UserServiceImpl;
import com.openclassrooms.mddapi.services.interfaces.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessagesServiceImpl messagesService;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/")
    public ResponseEntity<?> createMessage(@RequestBody MessageDto messageDto){
        Long postId = messageDto.getPost_id();
        Long userId = messageDto.getUser_id();

        Posts posts = postService.findById(postId);
        User user = userService.findById(userId);

        if (posts == null || user == null){
            return ResponseEntity.notFound().build();
        }
        if (messageDto.getMessage() == null) {
            return ResponseEntity.badRequest().build();
        }
        Messages messages = messageMapper.toEntity(messageDto);
        messagesService.createMessage(messages);
        return ResponseEntity.ok().build();
    }
}
