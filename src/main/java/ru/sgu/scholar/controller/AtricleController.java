package ru.sgu.scholar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sgu.scholar.dto.AuthorDTO;
import ru.sgu.scholar.service.ScholarService;

@RestController
@RequestMapping("article")
@RequiredArgsConstructor
public class AtricleController {

    private final ScholarService scholarService;

    @PostMapping
    public ResponseEntity<?> saveArticle(@RequestBody AuthorDTO authorDTO) {
        try {
            scholarService.saveAuthorInfo(authorDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
