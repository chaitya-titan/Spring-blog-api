package com.springpractice.blogapi.articles;

import com.springpractice.blogapi.dto.ArticleResponseDTO;
import com.springpractice.blogapi.dto.CreateArticleDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {
    private ArticleService articleService;

//    @GetMapping("")
//    public String getArticles() {
//        return "Hello World";
//    }
//
//    @GetMapping("/private")
//    public String getPrivateArticles() {
//        return "Hello Private World";
//    }

    @PostMapping("")
    public ResponseEntity<ArticleResponseDTO> createArticle(@RequestBody CreateArticleDTO createArticleDTO) {
        ArticleResponseDTO articleResponseDTO = articleService.createArticle(createArticleDTO);
        return ResponseEntity
                .created(URI.create("/articles" + articleResponseDTO.getId()))
                .body(articleService.createArticle(createArticleDTO));
    }

}
