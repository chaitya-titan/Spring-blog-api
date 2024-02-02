package com.springpractice.blogapi.comments;

import com.springpractice.blogapi.articles.ArticleEntity;
import com.springpractice.blogapi.articles.ArticleRepository;
import com.springpractice.blogapi.dto.CreateCommentDTO;
import com.springpractice.blogapi.security.jwt.JWTService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final JWTService jwtService;
    @Autowired
    private ModelMapper modelMapper;
    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository, JWTService jwtService) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.jwtService = jwtService;
    }

    public void deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<CreateCommentDTO> getComments(String articleSlug) {
        ArticleEntity articleEntity = articleRepository.findBySlug(articleSlug);
        Integer articleId = articleEntity.getId();
        List<CommentEntity> commentEntities = commentRepository.findByArticleId(articleId);
        List<CreateCommentDTO> createCommentDTOS = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            CreateCommentDTO createCommentDTO = modelMapper.map(commentEntity, CreateCommentDTO.class);
            createCommentDTOS.add(createCommentDTO);
        }
        return createCommentDTOS;
    }

    public CreateCommentDTO createComment(String articleSlug, CreateCommentDTO createCommentDTO) {
        String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        Integer authorId = jwtService.getUserIDFromJWT(token);
        ArticleEntity articleEntity = articleRepository.findBySlug(articleSlug);
        Integer articleId = articleEntity.getId();
        createCommentDTO.setCreatedAt(new Date());
        createCommentDTO.setArticleId(articleId);
        createCommentDTO.setAuthorId(authorId);
        CommentEntity commentEntity = modelMapper.map(createCommentDTO, CommentEntity.class);
        commentRepository.save(commentEntity);
        createCommentDTO.setId(commentEntity.getId());
        return createCommentDTO;
    }
}
