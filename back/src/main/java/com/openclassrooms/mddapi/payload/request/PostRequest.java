package com.openclassrooms.mddapi.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostRequest {

    @NotBlank
    String theme;

    @NotBlank
    String title;

    @NotBlank
    String description;
}
