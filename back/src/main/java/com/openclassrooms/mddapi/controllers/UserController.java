package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    /**
     * Permet de mettre à jour le pseudo et/ou l'email de l'utilisateur
     * @param id
     * @param newUserDto
     * @return
     */
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable("id") String id, @RequestBody UserDto newUserDto){
        try {
            userService.updateUser(id, newUserDto);
        } catch (Exception e){
            throw new BadRequestException();
        }
    }

    @GetMapping("/me")
    public UserDto getMe(Principal user){
        User userFromService = userService.getMe(user);
        return userMapper.toDto(userFromService);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable("id") String id){
        User userFromService = this.userService.getById(id);
        return userMapper.toDto(userFromService);
    }
}
