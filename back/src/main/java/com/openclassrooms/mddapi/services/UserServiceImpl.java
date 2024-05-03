package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname).orElse(null);
    }
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public void updateUser(String id, UserDto newUserDto){
        User user = findById(Long.valueOf(id));

        if (user == null) {
            throw new BadRequestException();
        }
        user.setEmail(newUserDto.getEmail());
        user.setNickname(newUserDto.getNickname());

        save(user);
    }

    @Override
    public User getMe(Principal user){
        String userMail = user.getName();
        return findByEmail(userMail);
    }

    @Override
    public User getById(String id){
        return findById(Long.valueOf(id));
    }
}
