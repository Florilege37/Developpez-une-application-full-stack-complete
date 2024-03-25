package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.PostsDto;
import com.openclassrooms.mddapi.entity.Posts;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PostMapper extends EntityMapper<PostsDto, Posts> {
}
