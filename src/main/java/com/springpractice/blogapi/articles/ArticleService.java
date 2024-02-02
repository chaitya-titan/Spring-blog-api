package com.springpractice.blogapi.articles;

import com.springpractice.blogapi.dto.ArticleResponseDTO;
import com.springpractice.blogapi.dto.CreateArticleDTO;
import com.springpractice.blogapi.security.jwt.JWTService;
import com.springpractice.blogapi.users.UserEntity;
import com.springpractice.blogapi.users.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    private final JWTService jwtService;


    @Autowired
    private ModelMapper modelMapper;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository, JWTService jwtService) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public ArticleResponseDTO getArticle(String articleSlug) {
        ArticleEntity articleEntity = articleRepository.findBySlug(articleSlug);
        return modelMapper.map(articleEntity, ArticleResponseDTO.class);
    }

    public List<ArticleResponseDTO> getAllArticles(int page, int size) {
        List<ArticleEntity> articleEntities = articleRepository.findAll();
        List<ArticleResponseDTO> articleResponseDTOS = new ArrayList<>();
        int expectedSize = (page-1) * size;
        if(articleEntities.size() < expectedSize) {
            return new ArrayList<>();
        }else{
            for(int i = expectedSize; i < expectedSize + size; i++) {
                if(i < articleEntities.size()) {
                    articleResponseDTOS.add(modelMapper.map(articleEntities.get(i), ArticleResponseDTO.class));
                }
            }
            return articleResponseDTOS;
        }
    }

    public ArticleResponseDTO getArticlesByAuthor(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        Integer authorId = userEntity.getId();
        ArticleEntity articleEntity = articleRepository.findByAuthorId(authorId);
        return modelMapper.map(articleEntity, ArticleResponseDTO.class);
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
