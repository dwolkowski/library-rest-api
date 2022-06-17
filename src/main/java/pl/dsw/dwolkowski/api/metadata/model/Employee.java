package pl.dsw.dwolkowski.api.metadata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity( name = "Employees" )
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employee_id;
    private String name;
    private String last_name;
    private String position;
    private Long boss_id;
    private double salary;
    private LocalDate employment_date;
    private String phone_number;
    private String address;
    private String email;

}
