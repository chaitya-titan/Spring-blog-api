package com.springpractice.blogapi.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    public List<CommentEntity> findByArticleId(Integer articleId);
}
