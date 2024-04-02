package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    /**
     * Permet de mettre à jour le pseudo et/ou l'email de l'utilisateur
     * @param id
     * @param newUserDto
     * @return
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody UserDto newUserDto){
        User user = userService.findById(Long.valueOf(id));

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserDto userDto = userMapper.toDto(user);
        userDto.setEmail(newUserDto.getEmail());
        userDto.setNickname(newUserDto.getNickname());
        user = userMapper.toEntity(userDto);

        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    ResponseEntity<?> getMe(Principal user){
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        String userMail = user.getName();
        User userResult = userService.findByEmail(userMail);
        return ResponseEntity.ok().body(userMapper.toDto(userResult));
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable("id") String id){
        User user = userService.findById(Long.valueOf(id));
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userMapper.toDto(user));
    }
}
