package pl.dsw.dwolkowski.api.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    // GET MAPPINGS
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation( summary = "Returns all employees from database." )
    public Collection<Employee> getAllEmployees(){ return employeeService.getAllEmployees(); }

    @GetMapping(path = "/{employee_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation( summary = "Returns employee with given employee_id." )
    @ApiResponse(responseCode = "404", description = "Employee Not Found")
    public Employee getEmployee(@PathVariable long employee_id){ return employeeService.getEmployee(employee_id); }

    // POST MAPPINGS
    @PostMapping(
            path = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation( summary = "Adds employee given in JSON format to database." )
    public Employee createEmployee(@RequestBody Employee newEmployee){ return employeeService.createEmployee(newEmployee); }

    // DELETE MAPPINGS
    @DeleteMapping(path = "/{employee_id}")
    @Operation( summary = "Deletes employee from database." )
    @ApiResponse(responseCode = "404", description = "Employee Not Found")
    public void deleteEmployee(@PathVariable long employee_id){ employeeService.deleteEmployee(employee_id); }
}