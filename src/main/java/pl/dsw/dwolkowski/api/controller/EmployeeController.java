package pl.dsw.dwolkowski.api.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.dsw.dwolkowski.api.metadata.model.Employee;
import pl.dsw.dwolkowski.api.service.EmployeeService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/library/v1/employee")
@Api(tags = "Employee Management")
@Tag(name = "Employee Management", description = "")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Employee> getAllEmployees(){
        return employeeService.listAllEmployees();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployee(@PathVariable long id){
        return employeeService.getEmployee(id);
    }

    @PostMapping(
            path = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Employee addEmployee(@RequestBody Employee newEmployee){
        return employeeService.addEmployee(newEmployee);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteEmployee(@PathVariable long id){
        employeeService.deleteEmployee(id);
    }
}
