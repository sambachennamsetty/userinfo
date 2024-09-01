package com.codedecode.userinfo.service;

import com.codedecode.userinfo.dto.UserDTO;
import com.codedecode.userinfo.entity.User;
import com.codedecode.userinfo.mapper.UserMapper;
import com.codedecode.userinfo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserDTO addUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.mapUserDTOToUser(userDTO);
        user = userRepo.save(user);
        return UserMapper.INSTANCE.mapUserToUserDTO(user);
    }

    public ResponseEntity<UserDTO> getUserByIdd(final Integer id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()) {
            return new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDTO(user.get()), HttpStatus.OK);}
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
