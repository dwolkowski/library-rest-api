package pl.dsw.dwolkowski.api.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.dsw.dwolkowski.api.metadata.model.Author;
import pl.dsw.dwolkowski.api.metadata.model.Book;
import pl.dsw.dwolkowski.api.service.BookService;

import java.util.Collection;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/library/v1/book")
@Api(tags = "Book Management")
@Tag(name = "Book Management", description = "")
public class BookController {

    private final BookService bookService;

    // GET MAPPINGS
    @GetMapping( path = "/all", produces = MediaType.APPLICATION_JSON_VALUE )
    @Operation( summary = "Returns all books from database." )
    public Collection<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/{book_id}", produces = MediaType.APPLICATION_JSON_VALUE )
    @Operation( summary = "Returns book with given id." )
    @ApiResponse(responseCode = "404", description = "Book Not Found")
    public Book findBookById(@PathVariable long book_id){
        return bookService.getBookById(book_id);
    }

    @GetMapping( path = "/authors", produces = MediaType.APPLICATION_JSON_VALUE )
    @Operation( summary = "Returns all authors of books stored in database." )
    public Collection<Author> getAllAuthors() {
        return bookService.getAllAuthors();
    }

    // POST MAPPINGS
    @PostMapping(
            path = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation( summary = "Adds book given in JSON format to database." )
    @ApiResponse(responseCode = "404", description = "Reader Not Found")
    public Book addBook(@RequestBody Book newBook){
        return bookService.addBook(newBook);
    }

    // PUT MAPPINGS
    @PutMapping(
            path = "/{book_id}/return",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation( summary = "Sets book with given id availability to true." )
    @ApiResponse(responseCode = "404", description = "Book Not Found")
    public Book setAvailable(@PathVariable long book_id){ return bookService.setAvailable(book_id); }

    @PutMapping(
            path = "/{book_id}/borrow/{reader_id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation( summary = "Sets book with given id availability to false and assigns reader." )
    @ApiResponse(responseCode = "400", description = "Book is Not Available")
    @ApiResponse(responseCode = "404", description = "Book or Reader Not Found")
    public Book setUnavailable(@PathVariable Long book_id, @PathVariable Long reader_id){
        return bookService.setUnavailable(book_id, reader_id);
    }

}

