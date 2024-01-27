package com.springpractice.blogapi.users;

import com.springpractice.blogapi.articles.ArticleEntity;
import com.springpractice.blogapi.commons.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "users")
public class UserEntity extends BaseEntity {

    @Column(unique = true, nullable = false, length = 50)
    String username;
    String password;  //TODO: hash this
    String email;
    String firstName;
    String lastName;
    String bio;
    String image;

    @ManyToMany(mappedBy = "likedBy")
    List<ArticleEntity> likedArticles;

    @ManyToMany
    @JoinTable(
            name = "user_follows",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    List<UserEntity> following;

    @ManyToMany(mappedBy = "following")
    List<UserEntity> followers;
}
