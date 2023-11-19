package ru.sgu.scholar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sgu.scholar.convertors.CitedByConvertorJson;
import ru.sgu.scholar.dto.AuthorResponse;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @Column(name = "author_id")
    private String authorId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Enumerated(EnumType.STRING)
    private Faculty faculty;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = CitedByConvertorJson.class)
    private AuthorResponse.CitedBy citedBy;
}
