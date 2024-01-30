package com.springpractice.blogapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class CreateUserDTO {
    private String email;
    private String username;
    private String password;
    private Date createdAt;
}
