package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.MessageDto;
import com.openclassrooms.mddapi.entity.Messages;
import com.openclassrooms.mddapi.repository.MessagesRepository;
import com.openclassrooms.mddapi.services.interfaces.MessagesService;
import com.openclassrooms.mddapi.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesServiceImpl implements MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private UserService userService;
    @Override
    public void createMessage(Messages messages) {
        messagesRepository.save(messages);
    }

    @Override
    public List<Messages> findAllMessageOfPostId(Long id) {
        return messagesRepository.findByPostId(id);
    }

    @Override
    public void loadUserNames(List<MessageDto> messages) {
        for(MessageDto message: messages){
            message.setUserName(userService.findById(message.getUser_id()).getNickname());
        }
    }
}
