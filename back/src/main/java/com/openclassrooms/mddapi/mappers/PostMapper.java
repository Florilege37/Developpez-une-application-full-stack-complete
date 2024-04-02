package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.MessageDto;
import com.openclassrooms.mddapi.dto.PostsDto;
import com.openclassrooms.mddapi.entity.Messages;
import com.openclassrooms.mddapi.entity.Posts;
import com.openclassrooms.mddapi.repository.MessagesRepository;
import com.openclassrooms.mddapi.services.interfaces.MessagesService;
import com.openclassrooms.mddapi.services.interfaces.PostService;
import com.openclassrooms.mddapi.services.interfaces.UserService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class PostMapper implements EntityMapper<PostsDto, Posts> {

    @Autowired
    private MessagesService messagesService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageMapper messageMapper;
    @Override
    public abstract PostsDto toDto(Posts posts);

    @AfterMapping
    protected void loadMessages(Posts posts, @MappingTarget PostsDto postsDto) {
        List<MessageDto> messages = messageMapper.toDto(messagesService.findAllMessageOfPostId(posts.getId()));
        messagesService.loadUserNames(messages);
        postsDto.setMessage(messages);
        postsDto.setUserName(userService.findById(posts.getUser_id()).getNickname());
    }
}
