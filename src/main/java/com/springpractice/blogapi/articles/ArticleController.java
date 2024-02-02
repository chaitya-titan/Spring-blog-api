package com.springpractice.blogapi.articles;

import com.springpractice.blogapi.dto.ArticleResponseDTO;
import com.springpractice.blogapi.dto.CreateArticleDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static java.util.List.of;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {
    private ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<ArticleResponseDTO>> getArticlesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(articleService.getAllArticles(page, size));
    }

    @GetMapping(params = "author")
    public ResponseEntity<ArticleResponseDTO> getArticlesByAuthor(@RequestParam String author) {
        return ResponseEntity.ok(articleService.getArticlesByAuthor(author));
    }

    @GetMapping("/{article-slug}")
    public ResponseEntity<ArticleResponseDTO> getArticle(@PathVariable("article-slug") String articleSlug) {
        return ResponseEntity.ok(articleService.getArticle(articleSlug));
    }

    @PostMapping("")
    public ResponseEntity<ArticleResponseDTO> createArticle(@RequestBody CreateArticleDTO createArticleDTO) {
        ArticleResponseDTO articleResponseDTO = articleService.createArticle(createArticleDTO);
        return ResponseEntity
                .created(URI.create("/articles" + articleResponseDTO.getId()))
                .body(articleService.createArticle(createArticleDTO));
    }

}
