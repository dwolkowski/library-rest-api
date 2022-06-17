package pl.dsw.dwolkowski.api.metadata.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Books")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long book_id;
    private String title;
    private String publisher;
    private LocalDate release_date;
    private String language;
    private boolean available;
    private Long reader_id;
    private double price;

    @ManyToMany
    @JoinTable(
            name = "Books_Authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

}
