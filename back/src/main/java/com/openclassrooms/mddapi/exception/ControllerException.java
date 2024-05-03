package com.openclassrooms.mddapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerException {


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> exceptionBadRequestHandler(BadRequestException badRequestException, WebRequest webRequest){
        return new ResponseEntity<Object>(badRequestException.getMessageResponse(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> exceptionNotFoundHandler(NotFoundException notFoundException, WebRequest webRequest){
        return ResponseEntity.notFound().build();
    }

}
