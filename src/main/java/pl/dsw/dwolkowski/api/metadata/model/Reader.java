package pl.dsw.dwolkowski.api.metadata.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "Readers")
@Getter
@Setter
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reader_id;
    private String name;
    private String last_name;
    private LocalDate birth_date;
    private String phone_number;
    private String address;
    private String email;

}