package ru.sgu.scholar.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.stereotype.Component;
import ru.sgu.scholar.entity.Article;
import ru.sgu.scholar.entity.Author;
import ru.sgu.scholar.dto.ArticleResponse;
import ru.sgu.scholar.dto.AuthorDTO;
import ru.sgu.scholar.dto.AuthorResponse;
import ru.sgu.scholar.repository.ArticleRepository;
import ru.sgu.scholar.repository.AuthorRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScholarService {
    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;
    private final SearchApi searchApi;
    private final ExecutorService executorService;

    public void saveAuthorInfo(AuthorDTO authorDTO) throws InterruptedException {
        AuthorResponse authorResponse = searchApi.authorSearch(authorDTO.authorId());
        AuthorResponse.CitedBy citedBy = authorResponse.getCitedBy();
        Author author;
        try {
            author = authorRepository.save(new Author(authorDTO.authorId(), authorDTO.authorName(), authorDTO.department(), authorDTO.faculty(), citedBy));
            log.info("Saved author: {}", author.getAuthorId());
        } catch (OptimisticEntityLockException e) {
            author = authorRepository.getReferenceById(authorDTO.authorId());
            log.info("Took author: {} from DB", author.getAuthorId());
        }
        Author finalAuthor = author;

        executorService.invokeAll(authorResponse.getArticles().stream().map(article -> (Callable<Void>) () -> {
                ArticleResponse.Citation citation = searchApi.searchArticle(article.getCitationId()).getCitation();
                saveArticle(article, citation, finalAuthor);
                return null;
            })
            .toList());
    }

    private void saveArticle(AuthorResponse.Article article, ArticleResponse.Citation citation, Author finalAuthor) {
        if (articleRepository.getArticleByCitationId(article.getCitationId()) == null) {
            articleRepository.save(new Article(article.getTitle(), citation.getDescription(), citation.getLink(), article.getCitationId(), finalAuthor, Integer.parseInt(article.getYear()), citation.getTotalCitations()));
            log.info("Saved the article: {} with citation id: {}", article.getTitle(), article.getCitationId());
        } else {
            log.info("The article: {} with citation id: {} was already in DB", article.getTitle(), article.getCitationId());
        }
    }
}
