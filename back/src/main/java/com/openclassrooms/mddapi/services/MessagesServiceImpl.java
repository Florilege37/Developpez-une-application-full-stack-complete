package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.entity.Messages;
import com.openclassrooms.mddapi.repository.MessagesRepository;
import com.openclassrooms.mddapi.services.interfaces.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagesServiceImpl implements MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;
    @Override
    public void createMessage(Messages messages) {
        messagesRepository.save(messages);
    }
}
