package ru.sgu.scholar.service;

import ru.sgu.scholar.dto.AuthorResponse;
import ru.sgu.scholar.dto.ArticleResponse;

public interface SearchApi {

    ArticleResponse searchArticle(String link);

    AuthorResponse authorSearch(String authorId);
}
