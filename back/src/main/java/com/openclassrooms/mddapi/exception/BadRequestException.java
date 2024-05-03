package com.openclassrooms.mddapi.exception;

import com.openclassrooms.mddapi.payload.response.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value= HttpStatus.BAD_REQUEST)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BadRequestException extends RuntimeException {
    private MessageResponse messageResponse;
}
