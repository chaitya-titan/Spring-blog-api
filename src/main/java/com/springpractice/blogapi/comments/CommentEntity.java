package com.springpractice.blogapi.comments;

import com.springpractice.blogapi.articles.ArticleEntity;
import com.springpractice.blogapi.commons.BaseEntity;
import com.springpractice.blogapi.users.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity(name = "comments")
public class CommentEntity extends BaseEntity {
    @Column(nullable = false, length = 100)
    String title;
    @Column(length = 8000)
    String body;

    @ManyToOne
    UserEntity author;

    @ManyToOne
    ArticleEntity article;
}
