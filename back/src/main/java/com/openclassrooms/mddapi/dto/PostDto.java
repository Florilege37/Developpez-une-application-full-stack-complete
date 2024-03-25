package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;

    @Size(max = 40)
    private String title;

    @Size(max = 2000)
    private String description;

    private Long user_id;

    private Long topic_id;

    private LocalDateTime created_at;
}
