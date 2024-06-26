package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.TopicsDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.entity.Posts;
import com.openclassrooms.mddapi.entity.Topics;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.services.PostServiceImpl;
import com.openclassrooms.mddapi.services.TopicsServiceImpl;
import com.openclassrooms.mddapi.services.UserServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring", uses = {UserServiceImpl.class}, imports = {Arrays.class, Collectors.class, Posts.class, User.class, Collections.class, Optional.class})
public abstract class UserMapper implements EntityMapper<UserDto, User> {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    PostServiceImpl postService;

    @Autowired
    TopicsServiceImpl topicsService;

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nickname", target = "nickname"),
            @Mapping(source = "email", target = "email"),
            @Mapping(target = "topicSubscribed", expression = "java(this.topicsService.getAllTopics())"),

    })
    public abstract User toEntity(UserDto userDto);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nickname", target = "nickname"),
            @Mapping(source = "email", target = "email"),
            @Mapping(target = "topicSubscribed", expression = "java(Optional.ofNullable(user.getTopicSubscribed()).orElseGet(Collections::emptyList).stream().map(u -> u.getId()).collect(Collectors.toList()))"),
    })
    public abstract UserDto toDto(User user);
}
