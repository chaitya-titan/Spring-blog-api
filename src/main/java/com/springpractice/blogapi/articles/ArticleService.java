package com.springpractice.blogapi.articles;

import com.springpractice.blogapi.dto.ArticleResponseDTO;
import com.springpractice.blogapi.dto.CreateArticleDTO;
import com.springpractice.blogapi.security.jwt.JWTAuthentication;
import com.springpractice.blogapi.security.jwt.JWTService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    private final JWTService jwtService;

//    private final JWTAuthentication jwtAuthentication;

    @Autowired
    private ModelMapper modelMapper;

    public ArticleService(ArticleRepository articleRepository, JWTService jwtService) {
        this.articleRepository = articleRepository;
        this.jwtService = jwtService;
    }

    public ArticleResponseDTO createArticle(CreateArticleDTO createArticleDTO) {
        //TODO: Check if slug is unique
        String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        Integer userId = jwtService.getUserIDFromJWT(token);
        createArticleDTO.setAuthorId(userId);
        ArticleEntity articleEntity = modelMapper.map(createArticleDTO, ArticleEntity.class);
        articleEntity.setSlug(createSlug(createArticleDTO.getTitle()));
        articleEntity.setCreatedAt(new Date());
        ArticleEntity savedArticleEntity = articleRepository.save(articleEntity);
        return modelMapper.map(savedArticleEntity, ArticleResponseDTO.class);
    }

    protected String createSlug(String title) {
        return title.toLowerCase().replaceAll(" ", "-");
    }
}
