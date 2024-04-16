package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

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
        return userService.updateUser(id, newUserDto);
    }

    @GetMapping("/me")
    ResponseEntity<?> getMe(Principal user){
        return this.userService.getMe(user);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable("id") String id){
        return this.userService.getById(id);
    }
}
