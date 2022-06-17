package pl.dsw.dwolkowski.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dsw.dwolkowski.api.metadata.model.Employee;
import pl.dsw.dwolkowski.api.repository.EmployeeRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee getEmployee(long id){ return employeeRepository.getById(id); }

    public Collection<Employee> listAllEmployees(){ return employeeRepository.findAll(); }

    public Employee createEmployee(Employee newEmployee){ return employeeRepository.save(newEmployee); }

    public void deleteEmployee(long id){ employeeRepository.deleteById(id); }

    public Employee addEmployee(Employee newEmployee) { return employeeRepository.save(newEmployee); }
}
