package com.springpractice.blogapi.articles;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {
    private ArticleService articleService;

    @GetMapping("")
    public String getArticles() {
        return "Articles";
    }

    @GetMapping("/private")
    public String getPrivateArticles() {
        return "Private Articles";
    }

}
