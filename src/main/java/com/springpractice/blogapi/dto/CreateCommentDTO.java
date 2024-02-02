package com.springpractice.blogapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class CreateCommentDTO {
    Integer id;
    Date createdAt;
    String title;
    String body;
    Integer authorId;
    Integer articleId;
}
