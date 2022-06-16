package pl.dsw.dwolkowski.api.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dsw.dwolkowski.api.metadata.model.Book;
import pl.dsw.dwolkowski.api.service.BookService;

import java.util.Collection;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/library/v1/books")
@Api(tags = "Book Management")
@Tag(name = "Book Management", description = "Book related endpoints")
public class BookController {

    private final BookService bookService;

    @GetMapping( path = "/all", produces = MediaType.APPLICATION_JSON_VALUE )
    @Operation( summary = "Returns all books from database." )
    public Collection<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    @Operation( summary = "Returns book with ." )
    public Book findBookById(@PathVariable long id){
        return bookService.getBookById(id);
    }
}
