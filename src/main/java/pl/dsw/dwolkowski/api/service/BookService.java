package pl.dsw.dwolkowski.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dsw.dwolkowski.api.metadata.exception.BookNotAvailableException;
import pl.dsw.dwolkowski.api.metadata.exception.EntityNotFoundException;
import pl.dsw.dwolkowski.api.metadata.model.Author;
import pl.dsw.dwolkowski.api.metadata.model.Book;
import pl.dsw.dwolkowski.api.repository.AuthorRepository;
import pl.dsw.dwolkowski.api.repository.BookRepository;
import pl.dsw.dwolkowski.api.repository.ReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ReaderRepository readerRepository;

    @Cacheable(cacheNames = "book_cache")
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @Cacheable(cacheNames = "book_cache")
    public Book getBookById(long id){
        return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Book addBook(Book newBook){ return bookRepository.save(newBook); }

    public Book setAvailable(long book_id){
        Book book = bookRepository.findById(book_id).orElseThrow(EntityNotFoundException::new);
        book.setAvailable(true);
        book.setReader_id(null);
        return bookRepository.save(book);
    }

    public Book setUnavailable(Long book_id, Long reader_id){
        Book book = bookRepository.findById(book_id).orElseThrow(EntityNotFoundException::new);
        readerRepository.findById(reader_id).orElseThrow(EntityNotFoundException::new);

        if(book.isAvailable()){
            book.setAvailable(false);
            book.setReader_id(reader_id);
        } else
            throw new BookNotAvailableException();

        return bookRepository.save(book);
    }
}
