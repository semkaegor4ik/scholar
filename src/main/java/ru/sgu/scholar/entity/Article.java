package ru.sgu.scholar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sgu.scholar.convertors.TotalCitationsConvertorJson;
import ru.sgu.scholar.dto.ArticleResponse;

@Entity
@Data
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String url;
    private String citationId;
    @ManyToOne
    private Author author;
    private int year;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = TotalCitationsConvertorJson.class)
    private ArticleResponse.TotalCitations totalCitations;

    public Article(String name, String description, String url, String citationId, Author author, int year, ArticleResponse.TotalCitations totalCitations) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.citationId = citationId;
        this.author = author;
        this.year = year;
        this.totalCitations = totalCitations;
    }
}
