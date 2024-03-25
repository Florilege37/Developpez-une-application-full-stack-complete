package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.TopicsDto;
import com.openclassrooms.mddapi.entity.Topics;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface TopicsMapper extends EntityMapper<TopicsDto, Topics> {
}
