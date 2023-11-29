package com.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Article {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    @CreatedDate
    private LocalDate createdDate;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    public void update(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
    }
}
