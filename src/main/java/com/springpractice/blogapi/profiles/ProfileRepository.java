package com.springpractice.blogapi.profiles;

import com.springpractice.blogapi.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
}
