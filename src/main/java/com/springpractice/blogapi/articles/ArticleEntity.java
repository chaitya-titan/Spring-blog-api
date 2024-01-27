package com.springpractice.blogapi.articles;

import com.springpractice.blogapi.commons.BaseEntity;
import com.springpractice.blogapi.users.UserEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "articles")
public class ArticleEntity extends BaseEntity {
    @Column(unique = true, nullable = false, length = 150)
    String slug;
    @Column(nullable = false, length = 200)
    String title;
    @Column(nullable = false, length = 8000)
    String body;
//    String[] taglist; //TODO: implement this

    @ManyToOne
    UserEntity author;

    @ManyToMany
    @JoinTable(
            name = "article_likes",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<UserEntity> likedBy;
}
