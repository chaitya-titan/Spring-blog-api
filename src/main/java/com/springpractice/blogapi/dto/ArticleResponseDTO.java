package com.springpractice.blogapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ArticleResponseDTO {
    Integer id;
    String title;
    String slug;
    String body;
    Date createdAt;
    Integer authorId;
}
