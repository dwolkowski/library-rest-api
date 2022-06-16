package pl.dsw.dwolkowski.api.metadata.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "ksiazki")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_ksiazki;
    private String tytul;
    private Integer id_wydawnictwa;
    private LocalDate data_wydania;
    private String jezyk;
    private Double cena;

}
