package pl.dsw.dwolkowski.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.dsw.dwolkowski.api.metadata.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Books b LEFT JOIN FETCH b.authors")
    List<Book> findAllBooks();
}
