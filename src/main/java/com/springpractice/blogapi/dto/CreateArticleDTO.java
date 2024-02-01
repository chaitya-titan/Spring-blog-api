package com.springpractice.blogapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateArticleDTO {
    String title;
    String body;
    Integer authorId;
}
