package com.board.controller;

import com.board.entity.Article;
import com.board.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /* 모든 글 불러오기 */
    @GetMapping("/articles")
    public String index(Model model) {
        List<Article> articleList = articleService.index();
        model.addAttribute("articleList", articleList);
        return "index";
    }

    /* 글 작성 페이지 */
    @GetMapping("/articles/write")
    public String write() {
        return "articles/articles-write";
    }

    /* 글 보기 (조회수 +1) */
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        Article article = articleService.show(id);
        articleService.increaseViewCount(id);
        model.addAttribute("article", article);
        model.addAttribute("articleId", article.getId());
        return "articles/articles-view";
    }

    /* 글 수정 */
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
       Article article = articleService.show(id);
       model.addAttribute("article", article);
       model.addAttribute("articleId", article.getId());
       return "articles/articles-update";
    }
}
