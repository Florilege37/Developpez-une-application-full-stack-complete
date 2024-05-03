package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.MessageDto;
import com.openclassrooms.mddapi.entity.Messages;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.services.interfaces.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessagesService messagesService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void createMessage(@RequestBody MessageDto messageDto){
        Optional<Messages> message = messagesService.createMessage(messageDto);
        if (message.isEmpty()){
            throw new BadRequestException();
        }
    }
}
