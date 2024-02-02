package com.springpractice.blogapi.comments;

import com.springpractice.blogapi.dto.CreateCommentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/articles/{article-slug}/comments")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("")
    public ResponseEntity<List<CreateCommentDTO>> getComments(@PathVariable("article-slug") String articleSlug) {
        return ResponseEntity.ok(commentService.getComments(articleSlug));
    }

    @PostMapping("")
    public ResponseEntity<CreateCommentDTO> createComment(
            @PathVariable("article-slug") String articleSlug,
            @RequestBody CreateCommentDTO createCommentDTO) {
        return ResponseEntity
                .created(URI.create(""))
                .body(commentService.createComment(articleSlug, createCommentDTO));
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("comment-id") Integer commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
