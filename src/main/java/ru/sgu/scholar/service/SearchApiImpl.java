package ru.sgu.scholar.service;

import static ru.sgu.scholar.utils.RequestParams.API_KEY_PARAM;
import static ru.sgu.scholar.utils.RequestParams.AUTHOR_ID_PARAM;
import static ru.sgu.scholar.utils.RequestParams.CITATION_ID_PARAM;
import static ru.sgu.scholar.utils.RequestParams.ENGINE_PARAM;
import static ru.sgu.scholar.utils.RequestParams.NUM_PARAM;
import static ru.sgu.scholar.utils.RequestParams.VIEW_OP_PARAM;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.sgu.scholar.config.ScholarConfig;
import ru.sgu.scholar.dto.ArticleResponse;
import ru.sgu.scholar.dto.AuthorResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchApiImpl implements SearchApi {
    private static final String ENGINE = "google_scholar_author";
    private static final String VIEW_OP = "view_citation";
    private static final int NUM = 100;
    private final RestTemplate restTemplate;
    private final ScholarConfig config;

    @Override
    public ArticleResponse searchArticle(String citationId) {
        log.info("Getting info about the article with citation id: {}", citationId);

        String articleUrl = UriComponentsBuilder.fromHttpUrl(config.getApi())
            .queryParam(ENGINE_PARAM, ENGINE)
            .queryParam(VIEW_OP_PARAM, VIEW_OP)
            .queryParam(API_KEY_PARAM, config.getApiKey())
            .queryParam(CITATION_ID_PARAM, citationId)
            .encode()
            .toUriString();

        ArticleResponse article = restTemplate.getForObject(articleUrl, ArticleResponse.class);
        log.info("Got info about the article with citation id: {}", citationId);
        return article;
    }

    @Override
    public AuthorResponse authorSearch(String authorId) {
        log.info("Getting info about the author: {}", authorId);

        String authorUrl = UriComponentsBuilder.fromHttpUrl(config.getApi())
            .queryParam(ENGINE_PARAM, ENGINE)
            .queryParam(NUM_PARAM, NUM)
            .queryParam(API_KEY_PARAM, config.getApiKey())
            .queryParam(AUTHOR_ID_PARAM, authorId)
            .encode()
            .toUriString();

        AuthorResponse authorResponse = restTemplate.getForObject(authorUrl, AuthorResponse.class);
        log.info("Got info about the author: {}", authorId);

        return authorResponse;
    }
}
