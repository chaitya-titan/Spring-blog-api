package com.springpractice.blogapi.users;

import com.springpractice.blogapi.dto.CreateUserDTO;

import com.springpractice.blogapi.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
        var user = userService.createUser(createUserDTO);
        return ResponseEntity
                .created(URI.create("/users/" + user.getId()))
                .body(user);
    }
}
