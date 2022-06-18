package pl.dsw.dwolkowski.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.dsw.dwolkowski.api.metadata.exception.EntityNotFoundException;
import pl.dsw.dwolkowski.api.metadata.model.Employee;
import pl.dsw.dwolkowski.api.repository.EmployeeRepository;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee getEmployee(long employee_id){ return employeeRepository.findById(employee_id).orElseThrow(EntityNotFoundException::new); }

    @Cacheable(cacheNames = "employee_cache")
    public Collection<Employee> getAllEmployees(){ return employeeRepository.findAll(); }

    public Employee createEmployee(Employee newEmployee){ return employeeRepository.save(newEmployee); }

    public void deleteEmployee(long employee_id){
        employeeRepository.findById(employee_id).orElseThrow(EntityNotFoundException::new);
        employeeRepository.deleteById(employee_id);
    }
}
