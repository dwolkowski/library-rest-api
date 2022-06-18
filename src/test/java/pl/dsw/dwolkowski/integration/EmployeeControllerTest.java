package pl.dsw.dwolkowski.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.dsw.dwolkowski.api.metadata.model.Employee;
import pl.dsw.dwolkowski.api.repository.EmployeeRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@Transactional
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee testEmployee;

    @BeforeEach
    private void setup(){
        if( testEmployee == null || !employeeRepository.existsById(testEmployee.getEmployee_id())){
            testEmployee = new Employee();
            testEmployee.setName("Test");
            testEmployee.setLast_name("Test");
            testEmployee.setPosition("Test");
            testEmployee.setSalary(9999);
            testEmployee.setEmployment_date(LocalDate.now());
            testEmployee.setPhone_number("Test");
            testEmployee.setAddress("Test");
            testEmployee.setEmail("Test");

            employeeRepository.save(testEmployee);
        }
    }

    @Test
    void getAllEmployees() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/library/v1/employee/all"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        List<Employee> employeeResponse = Arrays.asList(
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Employee[].class));

        assertThat(employeeResponse).isNotNull();
        assertThat(employeeResponse.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void getEmployee() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/library/v1/employee/" + testEmployee.getEmployee_id()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        Employee employeeResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Employee.class);

        assertThat(employeeResponse).isNotNull();
        assertThat(employeeResponse.getEmployee_id()).isEqualTo(testEmployee.getEmployee_id());
    }

    @Test
    void createEmployee() throws Exception {
        employeeRepository.deleteById(testEmployee.getEmployee_id());

        MvcResult mvcResult = mockMvc.perform(post("/api/library/v1/employee/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testEmployee)))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        Employee employeeResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Employee.class);

        assertThat(employeeResponse).isNotNull();
    }

    @Test
    void deleteEmployee() throws Exception {
        if(!employeeRepository.existsById(testEmployee.getEmployee_id()))
            employeeRepository.save(testEmployee);

        mockMvc.perform(delete("/api/library/v1/employee/" + testEmployee.getEmployee_id()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        assertThat(employeeRepository.existsById(testEmployee.getEmployee_id())).isFalse();
    }
}