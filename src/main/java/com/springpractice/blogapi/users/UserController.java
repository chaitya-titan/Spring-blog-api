package com.springpractice.blogapi.users;

import com.springpractice.blogapi.dto.CreateUserDTO;

import com.springpractice.blogapi.dto.LoginUserDTO;
import com.springpractice.blogapi.dto.UserResponseDTO;
import com.springpractice.blogapi.exception.IncorrectPasswordException;
import com.springpractice.blogapi.exception.UserNotFoundException;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;

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

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(@RequestBody LoginUserDTO loginUserDTO){
        var user = userService.loginUser(loginUserDTO);
        return ResponseEntity
                .ok(user);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<String> handleIncorrectPasswordException(IncorrectPasswordException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
