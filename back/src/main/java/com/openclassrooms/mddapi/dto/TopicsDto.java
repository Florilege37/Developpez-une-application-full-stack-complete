package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicsDto {
    private Long id;

    @Size(max = 40)
    private String theme;

    @Size(max = 2000)
    private String description;
}
