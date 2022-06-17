package pl.dsw.dwolkowski.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dsw.dwolkowski.api.metadata.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
