package com.springpractice.blogapi.users;

import com.springpractice.blogapi.users.dto.CreateUserDTO;
import com.springpractice.blogapi.users.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserResponseDTO createUser(CreateUserDTO createUserDTO) {
        //TODO: Implement this method
        return null;
    }
}
