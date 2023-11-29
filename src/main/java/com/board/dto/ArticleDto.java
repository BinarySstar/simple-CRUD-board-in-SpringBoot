package com.board.dto;

import com.board.entity.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private String writer;

    public Article toEntity() { //DB가 이해하기 편리한 entity로 전환
        return Article.builder()
                .id(getId())
                .title(title)
                .content(content)
                .writer(writer)
                .createdDate(LocalDate.now())
                .view(0)
                .build();
    }
}
