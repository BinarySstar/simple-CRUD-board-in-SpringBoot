package com.board.service;

import com.board.dto.ArticleDto;
import com.board.entity.Article;
import com.board.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    public List<Article> index() {
        return articleRepository.findAll();
    }
    @Transactional
    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Transactional
    public Article create(ArticleDto articleDto) {
        Article article = articleDto.toEntity();
        if(article.getId() != null){ // 데이터를 생성하는 과정이므로 id가 존재한다면 null 반환
            return null;
        }
        return articleRepository.save(article);
    }
    @Transactional
    public Article update(Long id, ArticleDto articleDto) {
        // 1. DTO -> Entity
        Article article = articleDto.toEntity();
        log.info("id : {}, article : {}", id, article.toString());
        log.info("article.getID() : {}", article.getId());
        // 2. 타깃 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리
        if(target == null || id != article.getId()){
            log.info("target : {}, id : {}, article.getId() : {}", target, id, article.getId());
            return null;
        }
        // 4. 업데이트
        target.update(article);
        Article updated = articleRepository.save(target);
        log.info("{}", updated.getTitle());
        return updated;
    }
    @Transactional
    public Article delete(Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        log.info("id : {}, target : {}", id, target);
        // 2. 잘못된 요청 처리
        if (target == null)
            return null;
        // 3. 대상 삭제
        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public int increaseViewCount(Long id) {
        return articleRepository.increaseViewCount(id);
    }
}
