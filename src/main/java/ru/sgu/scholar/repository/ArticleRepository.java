package ru.sgu.scholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sgu.scholar.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article getArticleByCitationId(String citationId);
}
