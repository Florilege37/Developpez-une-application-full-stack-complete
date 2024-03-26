package com.openclassrooms.mddapi.dto;

import com.openclassrooms.mddapi.entity.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsDto {

    private Long id;

    @Size(max = 40)
    private String title;

    @Size(max = 2000)
    private String description;

    private Long user_id;

    private Long topicId;

    private LocalDateTime created_at;

    private List<Messages> message;
}
