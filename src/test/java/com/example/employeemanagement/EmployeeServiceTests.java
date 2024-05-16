package com.example.employeemanagement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.employeemanagement.dto.request.EmployeeRequest;
import com.example.employeemanagement.dto.response.EmployeeResponse;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

@SpringBootTest
class EmployeeServiceTests {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    void saveEmployeeTest() {
        // Datos de prueba
        EmployeeRequest employeeRequest = new EmployeeRequest();
        // Mock del repositorio
        when(employeeRepository.save(any(Employee.class))).thenReturn(new Employee());
        // Prueba del método saveEmployee
        EmployeeResponse employeeResponse = employeeService.saveEmployee(employeeRequest);
        assertNotNull(employeeResponse);
    }

    @Test
    void findByIdTest() {
        // Datos de prueba
        Long id = 1L;
        Employee employee = new Employee();
        // Mock del repositorio
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        // Prueba del método findById
        EmployeeResponse employeeResponse = employeeService.findById(id);
        assertNotNull(employeeResponse);
    }

    @Test
    void findAllEmployeeTest() {
        // Datos de prueba
        List<Employee> employeeList = Arrays.asList(new Employee(), new Employee());
        // Mock del repositorio
        when(employeeRepository.findAll()).thenReturn(employeeList);
        // Prueba del método findAllEmployee
        List<EmployeeResponse> employeeResponseList = employeeService.findAllEmployee();
        assertNotNull(employeeResponseList);
        assertEquals(2, employeeResponseList.size());
    }

    @Test
    void updateEmployeeTest() {
        // Datos de prueba
        Long id = 1L;
        EmployeeRequest employeeRequest = new EmployeeRequest();
        // Mock del repositorio
        Employee existingEmployee = new Employee();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(new Employee());
        // Prueba del método updateEmployee
        EmployeeResponse employeeResponse = employeeService.updateEmployee(employeeRequest, id);
        assertNotNull(employeeResponse);
    }

    @Test
    void deleteEmployeeTest() {
        // Datos de prueba
        Long id = 1L;
        // Prueba del método deleteEmployee
        assertDoesNotThrow(() -> employeeService.deleteEmployee(id));
    }
}
