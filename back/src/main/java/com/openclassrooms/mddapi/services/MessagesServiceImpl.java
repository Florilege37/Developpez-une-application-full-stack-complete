package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.MessageDto;
import com.openclassrooms.mddapi.entity.Messages;
import com.openclassrooms.mddapi.entity.Posts;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.mappers.MessageMapper;
import com.openclassrooms.mddapi.repository.MessagesRepository;
import com.openclassrooms.mddapi.services.interfaces.MessagesService;
import com.openclassrooms.mddapi.services.interfaces.PostService;
import com.openclassrooms.mddapi.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessagesServiceImpl implements MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    MessageMapper messageMapper;
    @Override
    public Optional<Messages> createMessage(MessageDto messageDto) {
        Long postId = messageDto.getPostId();
        Long userId = messageDto.getUser_id();

        Posts posts = postService.findById(postId);
        User user = userService.findById(userId);

        if (posts == null || user == null || messageDto.getMessage() == null){
            return null;
        }

        Messages messages = messageMapper.toEntity(messageDto);
        messagesRepository.save(messages);
        return Optional.of(messages);
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
