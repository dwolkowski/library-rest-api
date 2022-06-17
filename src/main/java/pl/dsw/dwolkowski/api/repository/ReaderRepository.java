package pl.dsw.dwolkowski.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dsw.dwolkowski.api.metadata.model.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
}
