package com.board.repository;

import com.board.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    /* 조회 수 업데이트 */
    @Modifying
    @Query("UPDATE Article a SET a.view = a.view + 1 WHERE a.id = :id")
    int increaseViewCount(Long id);
}
