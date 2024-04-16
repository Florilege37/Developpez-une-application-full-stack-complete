package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.MessageDto;
import com.openclassrooms.mddapi.services.interfaces.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessagesService messagesService;

    @PostMapping("/create")
    public ResponseEntity<?> createMessage(@RequestBody MessageDto messageDto){
        return messagesService.createMessage(messageDto);
    }
}
