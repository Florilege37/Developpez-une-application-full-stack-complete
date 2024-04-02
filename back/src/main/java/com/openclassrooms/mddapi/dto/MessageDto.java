package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private Long id;

    private Long user_id;

    private String userName;

    private Long postId;
    @Size(max = 2000)
    private String message;
}
