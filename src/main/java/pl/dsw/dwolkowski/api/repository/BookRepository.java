package pl.dsw.dwolkowski.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dsw.dwolkowski.api.metadata.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
