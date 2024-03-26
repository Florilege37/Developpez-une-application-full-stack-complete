package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.MessageDto;
import com.openclassrooms.mddapi.entity.Messages;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MessageMapper extends EntityMapper<MessageDto, Messages> {
}
