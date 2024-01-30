package com.springpractice.blogapi.users;

import com.springpractice.blogapi.dto.CreateUserDTO;
import com.springpractice.blogapi.dto.LoginUserDTO;
import com.springpractice.blogapi.dto.UserResponseDTO;
import com.springpractice.blogapi.exception.IncorrectPasswordException;
import com.springpractice.blogapi.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public UserResponseDTO createUser(CreateUserDTO createUserDTO) {
        //TODO: Encrypt password
        //TODO: Validate Email
        //TODO: Check if username already exists
        createUserDTO.setCreatedAt(new Date());
        UserEntity newUserEntity = modelMapper.map(createUserDTO, UserEntity.class);
        //Hashing my password below
        newUserEntity.setPassword(passwordEncoder.encode(newUserEntity.getPassword()));
        System.out.println(newUserEntity);
        UserEntity savedUser = userRepository.save(newUserEntity);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO){
        UserEntity userEntity = userRepository.findByUsername(loginUserDTO.getUsername());
        if(userEntity == null){
            throw new UserNotFoundException("User not found");
        }
        boolean passMatch = passwordEncoder.matches(loginUserDTO.getPassword(), userEntity.getPassword());
        if(!passMatch){
            throw new IncorrectPasswordException(userEntity.getUsername());
        }
        return modelMapper.map(userEntity, UserResponseDTO.class);
    }

}
