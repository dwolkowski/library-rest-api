package pl.dsw.dwolkowski.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dsw.dwolkowski.api.metadata.model.Book;
import pl.dsw.dwolkowski.api.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Cacheable(cacheNames = "default")
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(long id){
        return bookRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }


}
