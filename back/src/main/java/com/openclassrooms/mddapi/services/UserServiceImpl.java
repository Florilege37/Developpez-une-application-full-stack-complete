package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.entity.User;
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

    @Autowired
    private UserMapper userMapper;
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
    public ResponseEntity<?> updateUser(String id, UserDto newUserDto){
        User user = findById(Long.valueOf(id));

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        UserDto userDto = userMapper.toDto(user);
        userDto.setEmail(newUserDto.getEmail());
        userDto.setNickname(newUserDto.getNickname());
        user = userMapper.toEntity(userDto);

        save(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> getMe(Principal user){
        if (user == null){
            return ResponseEntity.badRequest().build();
        }
        String userMail = user.getName();
        User userResult = findByEmail(userMail);
        return ResponseEntity.ok().body(userMapper.toDto(userResult));
    }

    @Override
    public ResponseEntity<?> getById(String id){
        User user = findById(Long.valueOf(id));
        if (user == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(userMapper.toDto(user));
    }
}
