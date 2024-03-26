package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.PostsDto;
import com.openclassrooms.mddapi.entity.Posts;
import com.openclassrooms.mddapi.repository.MessagesRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class PostMapper implements EntityMapper<PostsDto, Posts> {

    @Autowired
    private MessagesRepository messageRepository;
    @Override
    public abstract PostsDto toDto(Posts posts);

    @AfterMapping
    protected void loadMessages(Posts posts, @MappingTarget PostsDto postsDto) {
        postsDto.setMessage(messageRepository.findByPostId(posts.getId()));
    }
}
