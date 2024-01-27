package com.springpractice.blogapi.users;

import com.springpractice.blogapi.dto.CreateUserDTO;
import com.springpractice.blogapi.dto.LoginUserDTO;
import com.springpractice.blogapi.dto.UserResponseDTO;
import com.springpractice.blogapi.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserResponseDTO createUser(CreateUserDTO createUserDTO) {
        //TODO: Encrypt password
        //TODO: Validate Email
        //TODO: Check if username already exists
        UserEntity newUserEntity = modelMapper.map(createUserDTO, UserEntity.class);
        System.out.println(newUserEntity);
        UserEntity savedUser = userRepository.save(newUserEntity);
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO){
        UserEntity userEntity = userRepository.findByUsername(loginUserDTO.getUsername());
        if(userEntity == null){
            throw new UserNotFoundException("User not found");
        }
        //TODO: Encrypt password
        if(!userEntity.getPassword().equals(loginUserDTO.getPassword())){
            throw new UserNotFoundException("Password is incorrect");
        }

        return modelMapper.map(userEntity, UserResponseDTO.class);
    }
}
