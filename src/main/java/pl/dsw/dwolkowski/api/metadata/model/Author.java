package pl.dsw.dwolkowski.api.metadata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Authors")
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long author_id;
    private String name;
    private String last_name;
    private LocalDate birth_date;
    private String nationality;

    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

}
