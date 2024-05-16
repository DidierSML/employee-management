package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.request.EmployeeRequest;
import com.example.employeemanagement.dto.response.EmployeeResponse;
import com.example.employeemanagement.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    EmployeeResponse saveEmployee (EmployeeRequest employeeRequest);
    List<EmployeeResponse> findAllEmployee ();
    EmployeeResponse findById (Long id);
    EmployeeResponse updateEmployee (EmployeeRequest employeeRequest, Long id);
    void deleteEmployee(Long id);
}
