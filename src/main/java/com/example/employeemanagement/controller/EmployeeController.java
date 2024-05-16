package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.EmployeeMapper;
import com.example.employeemanagement.dto.request.EmployeeRequest;
import com.example.employeemanagement.dto.response.EmployeeResponse;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api")
public class EmployeeController {

    private final EmployeeService employeeService ;


    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @PostMapping("saveEmployee")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse saveEmpResponse(@RequestBody EmployeeRequest employeeRequest){
        return  employeeService.saveEmployee(employeeRequest);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse findEmployeeById (@PathVariable("id") Long id){

        return employeeService.findById(id);
    }

    @GetMapping("getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeResponse> findAllEmployee () {
        return employeeService.findAllEmployee();
    }

    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponse updateEmployee (@RequestBody EmployeeRequest employeeRequest,
                                              @PathVariable("id") Long id){
        return  employeeService.updateEmployee(employeeRequest, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
    }

}
