package com.springpractice.blogapi.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateUserDTO {
    private String email;
    private String username;
    private String password;
}
