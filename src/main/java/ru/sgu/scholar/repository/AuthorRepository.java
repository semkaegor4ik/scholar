package ru.sgu.scholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sgu.scholar.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, String> {
}
