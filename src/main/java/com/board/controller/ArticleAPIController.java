package com.board.controller;

import com.board.dto.ArticleDto;
import com.board.entity.Article;
import com.board.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleAPIController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/api/articles")
    public List<Article> index(){ // 게시글 전체 조회
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){ // 특정 게시글 조회
        return articleService.show(id);
    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleDto articleDto){
        Article created = articleService.create(articleDto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleDto articleDto){
        Article updated = articleService.update(id, articleDto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
