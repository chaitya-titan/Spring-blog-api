package com.springpractice.blogapi.articles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
    public ArticleEntity findBySlug(String slug);

    public ArticleEntity findByAuthorId(Integer id);
}
