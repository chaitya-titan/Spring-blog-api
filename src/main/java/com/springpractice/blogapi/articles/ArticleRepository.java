package com.springpractice.blogapi.articles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
    public ArticleEntity findBySlug(String slug);

    public ArticleEntity findByAuthorId(Integer id);
}
