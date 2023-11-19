package ru.sgu.scholar.dto;

import ru.sgu.scholar.entity.Department;
import ru.sgu.scholar.entity.Faculty;

public record AuthorDTO(
    String authorName,
    String authorId,
    Faculty faculty,
    Department department
) {
}
